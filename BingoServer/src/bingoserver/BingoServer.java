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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class BingoServer implements ClientListener {

    private final GameDelegate delegate;
    private static final int PORT = 10001;

    private final BlockingQueue<Runnable> taskQueue;
    
    public BingoServer() {
        this.delegate = new GameDelegate();
        this.taskQueue = new LinkedBlockingDeque<>();
    }

    private class Clock extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    boolean enqueued = taskQueue.offer(new Runnable() {
                        @Override
                        public void run() {
                            BingoServer.this.delegate.onClockTick();
                        }
                    });
                    
                    if (!enqueued) {
                        Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, "Failed to enqueue clock tick task");
                    }
                    
                    // O onClockTick será enfileirado.
                    // Por isso pode existir uma latência até a tarefa ser executada.
                    // Consideramos a latência como 5ms
                    Thread.sleep(995);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        
        new Clock().start();

        try {
            while (true) {
                Runnable task = taskQueue.take();
                task.run();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clientReceiver.stop();
    }

    @Override
    public void onClientConnected(final Client c) {
        boolean enqueued = taskQueue.offer(new Runnable() {
            @Override
            public void run() {
                delegate.onClientConnected(c);
            }
        });
        
        if (!enqueued) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, "Failed to enqueue a client connection task.");
        }
    }

    @Override
    public void onClientDisconnected(final Client client) {
        boolean enqueued = taskQueue.offer(new Runnable() {
            @Override
            public void run() {
                delegate.onClientDisconnected(client);
            }
        });
        
        if (!enqueued) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, "Failed to enqueue a client disconnect task.");
        }
    }

    @Override
    public void onClientMessage(final Client client, final String message) {
        boolean enqueued = taskQueue.offer(new Runnable() {
            @Override
            public void run() {
                delegate.onClientMessage(client, message);
            }
        });
        
                
        if (!enqueued) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, "Failed to enqueue client message task.");
        }
    }
}
