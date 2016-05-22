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
public class ClientReceiver implements Runnable {

    private final ServerSocket serverSock;
    private final ClientListener clientListener;
    private final Thread thread;

    public ClientReceiver(int port, ClientListener clientListener) throws IOException {
        this.serverSock = new ServerSocket(port);
        this.clientListener = clientListener;
        this.thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void stop() throws IOException {
        serverSock.close();
    }

    @Override
    public void run() {
        while (true) {
            Socket socket;

            try {
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "Waiting new Client");
                socket = serverSock.accept();

                Logger.getLogger(ClientReceiver.class.getName()).log(Level.INFO, "New Client Connected");
                Client c = new Client(socket, clientListener);
                c.start();
            } catch (IOException ex) {
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
}
