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
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class Client implements Cloneable {

    private interface ResponderListener {

        void onSendError();
    }

    private class ClientReponder implements Runnable {

        private final Socket socket;
        private final ResponderListener listener;
        private final Queue<String> outputQueue;

        ClientReponder(Socket socket, ResponderListener listener) {
            this.socket = socket;
            this.outputQueue = new LinkedBlockingQueue<>();
            this.listener = listener;
        }

        void start() {
            // ....
        }

        synchronized void send(String data) {
            outputQueue.add(data);
            // this.notify();
            run();
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
                String message = outputQueue.poll();

                if (message != null) {
                    send(message, output);
                } else {
                    // this.wait();
                }
                // }
            } catch (IOException ex) {
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

    void send(Response resp) {
        responder.send(resp.responseData());
    }

    void readOneMessage() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        this.read(ois);
        ois.close();
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

    public Client(Client other) {
        if (other == null) {
            throw new NullPointerException("Client cannot be null");
        }

        this.listener = other.listener;
        this.socket = other.socket;
        this.responder = other.responder;
    }

    @Override
    public Object clone() {
        Client newClient = null;
        try {
            newClient = new Client(this);
        } catch (Exception e) {
        }

        return newClient;
    }

    void start() {
        listener.onClientConnected(this);
        responder.start();
    }

    void stop() {
        listener.onClientDisconnected(this);

        try {
            // Vai automaticamente parar o responder nesse momento
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
