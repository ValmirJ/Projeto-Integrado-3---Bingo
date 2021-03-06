/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

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
 * @author 14023691
 */
public class Client implements Runnable {

    private class ClientReponder implements Runnable {

        private final Socket socket;
        private final BlockingQueue<String> outputQueue;

        ClientReponder(Socket socket) {
            this.socket = socket;
            this.outputQueue = new LinkedBlockingDeque<>();
        }

        void start() {
            new Thread(this).start();
        }

        void send(String data) {
            if (data == null) {
                throw new NullPointerException("Data cannot be null");
            }

            if (!outputQueue.add(data)) {
                // A Fila está cheia.
                // Nesse momento algo muito errado está acontecendo.
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Send Queue Full");
                stopSilent();
            }
        }

        private void send(String data, ObjectOutputStream output) throws IOException {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Sending response {0}", data);
            output.writeUTF(data);
            output.flush();
        }

        @Override
        public void run() {
            ObjectOutputStream output = null;
            try {
                output = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    send(outputQueue.take(), output);
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                stopSilent();
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
        this.responder = new ClientReponder(socket);
    }

    @Override
    public void run() {
        try {
            this.readMessages();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.stop();
        }
    }

    void send(String resp) {
        responder.send(resp);
    }

    private void readMessages() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        for (;;) {
            this.read(ois);
        }
    }

    private void read(ObjectInputStream input) throws IOException {
        String message = input.readUTF();

        if (message != null) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Received request {0}", message);
            try {
                listener.onClientMessage(this, message);
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    public String toString() {
        String str = "Socket: " + this.socket.toString();
        return str;
    }

    void start() {
        listener.onClientConnected(this);
        responder.start();
        new Thread(this).start();
    }

    void stop() {
        try {
            listener.onClientDisconnected(this);
        } finally {
            stopSilent();
        }
    }

    void stopSilent() {
        try {
            // Vai automaticamente parar o responder nesse momento
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
