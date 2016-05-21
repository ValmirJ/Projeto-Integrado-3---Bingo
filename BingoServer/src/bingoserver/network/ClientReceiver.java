/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class ClientReceiver {

    private final ServerSocket serverSock;
    private final ClientReceiverListener receiverListener;
    private final ClientListener clientListener;
    private boolean running;

    public ClientReceiver(int port, ClientReceiverListener receiverListener, ClientListener clientListener) throws IOException {
        this.serverSock = new ServerSocket(port);
        this.receiverListener = receiverListener;
        this.clientListener = clientListener;

        running = false;
    }

    public void start() {
        running = true;

        while (running) {
            Socket socket;

            try {
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "Waiting new Client");
                socket = serverSock.accept();
            } catch (IOException ex) {
                socket = null;
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                this.stop();
            }

            if (socket != null) {
                try {
                    Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "New Client Connected");
                    Client c = new Client(socket, clientListener);
                    receiverListener.onClientConnected(c);
                } catch (IOException ex) {
                    Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void stop() {
        running = false;
    }
}
