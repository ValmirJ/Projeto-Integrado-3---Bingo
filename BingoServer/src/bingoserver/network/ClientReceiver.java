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
    private final ClientListener clientListener;

    public ClientReceiver(int port, ClientListener clientListener) throws IOException {
        if (clientListener == null) {
            throw new NullPointerException("ClientListener cannot be null");
        }

        this.serverSock = new ServerSocket(port);
        this.clientListener = clientListener;
    }

    public void start() {
        waitForClients();
    }

    public void stop() throws IOException {
        serverSock.close();
    }

    private void waitForClients() {
        while (true) {
            Socket socket;

            try {
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "Waiting new Client");
                socket = serverSock.accept();
                socket.setTcpNoDelay(true);

                Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "New Client Connected");
                try {
                    Client c = new Client(socket, clientListener);
                    c.start();

                    // Evitando uso de threads
                    c.readOneMessage();

                    c.stop();
                } catch (IOException ex) {
                    Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
