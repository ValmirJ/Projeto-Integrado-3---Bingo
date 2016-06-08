/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import bingoserver.responses.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class Client implements Runnable {

    private interface ResponderListener {

        void onSendError();
    }

    private class ClientReponder implements Runnable {

        private final Socket socket;
        private final ResponderListener listener;
        private final BlockingQueue<String> outputQueue;

        ClientReponder(Socket socket, ResponderListener listener) {
            this.socket = socket;
            this.outputQueue = new LinkedBlockingDeque<>();
            this.listener = listener;
        }

        void start() {
            // ....
        }

        void send(String data) {
            if (data == null) {
                throw new NullPointerException("Data cannot be null");
            }

            if (!outputQueue.add(data)) {
                // A Fila está cheia.
                // Nesse momento algo muito errado está acontecendo.
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Send Queue Full");
                listener.onSendError();
            }
        }

        private void send(String data, ObjectOutputStream output) throws IOException {
            output.writeUTF(data);
        }

        @Override
        public void run() {
            ObjectOutputStream output = null;

            try {
                output = new ObjectOutputStream(socket.getOutputStream());

                // while (true) {
                send(outputQueue.take(), output);
                // }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                listener.onSendError();
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                listener.onSendError();
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private final Socket socket;
    private final ClientReponder responder;
    private final ClientListener listener;
    private boolean isStoped = false;

    Client(Socket socket, ClientListener listener) throws IOException {
        if (socket == null) {
            throw new NullPointerException("Socket cannot be null");
        }

        if (listener == null) {
            throw new NullPointerException("ClientListener cannot be null");
        }

        this.socket = socket;
        this.listener = listener;

        this.responder = new ClientReponder(socket, new ResponderListener() {
            @Override
            public void onSendError() {
                stop();
            }
        });

    }

    @Override
    public void run() {
        try {
            this.readMessages();
        } catch (IOException ex) {
            this.stop();
        }
    }   
    
    void send(Response resp) {
        responder.send(resp.responseData());
    }

    private void readMessages() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        for(;;) {
            this.read(ois);
        } 
    }

    private void read(ObjectInputStream input) throws IOException {
        String message = input.readUTF();

        if (message != null) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Received request {0}", message);
            listener.onClientMessage(this, message);
        } else {
            Logger.getLogger(Client.class.getName()).log(Level.WARNING, "Received null Client message");
            throw new IOException("Null client response");
        }
    }

    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + this.socket.hashCode();
        r = r * 7 + this.listener.hashCode();
        r = r * 7 + this.responder.hashCode();

        return r;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }

        Client other = (Client) obj;
        if (!(this.socket.equals(other.socket))) {
            return false;
        }
        if (!(this.listener.equals(other.listener))) {
            return false;
        }

        if (!(this.responder.equals(other.responder))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String str = "Socket: " + this.socket.toString();

        return str;
    }

    void start() {
        listener.onClientConnected(this);
        responder.start();
        new Thread(this).start();
    }

    synchronized void stop() {
        // Garante que esse listener seja chamado apenas uma vez.
        if(!this.isStoped) {
            listener.onClientDisconnected(this);
            this.isStoped = true;
        }

        try {
            // Vai automaticamente parar o responder nesse momento
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
