/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.network.Client;
import bingoserver.network.ClientListener;
import bingoserver.network.ClientReceiver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class BingoServer implements ClientListener {

    private GameDelegate delegate;
    private static final int PORT = 10001;

    public BingoServer() {
        this.delegate = new GameDelegate();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            (new BingoServer()).execute();
        } catch (IOException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void execute() throws IOException {
        ClientReceiver clientReceiver = new ClientReceiver(PORT, this);
        clientReceiver.start();

        boolean run = true;

        while (run) {
            try {
                Thread.sleep(1000);
                delegate.onClockTick();
            } catch (InterruptedException ex) {
                Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
                run = false;
            }
        }

        clientReceiver.stop();
    }

    @Override
    public void onClientConnected(Client c) {
        delegate.onClientConnected(c);
    }

    @Override
    public void onClientDisconnected(Client client) {
        delegate.onClientDisconnected(client);
    }

    @Override
    public void onClientMessage(Client client, String message) {
        delegate.onClientMessage(client, message);
    }
}
