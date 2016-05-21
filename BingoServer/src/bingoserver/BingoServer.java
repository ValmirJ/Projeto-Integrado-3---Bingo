/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.network.ClientListener;
import bingoserver.network.ClientReceiver;
import bingoserver.network.ClientReceiverListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class BingoServer {

    private static final int PORT = 10001;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameDelegate delegate = new GameDelegate();

        try {
            ClientReceiverListener receiverListener = delegate;
            ClientListener clientListener = delegate;

            ClientReceiver clientReceiver = new ClientReceiver(PORT, receiverListener, clientListener);
            clientReceiver.start();
        } catch (IOException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
