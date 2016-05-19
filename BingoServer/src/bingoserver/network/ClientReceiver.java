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
    private final ClientReceiverListener cList;
    private boolean running;
    
    public ClientReceiver(int port, ClientReceiverListener listener) throws IOException {
        serverSock = new ServerSocket(port);
        cList = listener;
        running = false;
    }
    
    public void start() {
        running = true;
        
        while (running) {
            Socket s;
            
            try {
                s = serverSock.accept();
            } catch (IOException ex) {
                s = null;
                Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                this.stop();
            }
            
            if (s != null) {
                try {
                    cList.onClientConnected(new Client(s));
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