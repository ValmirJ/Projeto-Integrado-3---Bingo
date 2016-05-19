/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.messages.Message;
import bingoserver.network.ClientListener;
import bingoserver.network.Client;
import bingoserver.network.ClientReceiverListener;
import bingoserver.repositories.RepositoryManager;

/**
 *
 * @author 15096134
 */
public class GameDelegate implements ClientListener, 
        ClientReceiverListener, 
        ClockListener
{
    private RepositoryManager repositoryManager;
    private final ClientsManager cManager;
    private final EventPerformer evtPerformer;
    private final EventBuilder evtBuilder;
    
    public GameDelegate() {
        cManager = new ClientsManager();
        evtPerformer = new EventPerformer();
        evtBuilder = new EventBuilder(repositoryManager, cManager);
    }
    
    @Override
    public void onClientDisconnected(Client c) {
        cManager.removeClient(c);
    }

    @Override
    public void onClientMessage(Client c, Message m) {
        Event evt = evtBuilder.createClientEvent(c, m);
        evtPerformer.process(evt);
    }

    @Override
    public void onClientConnected(Client c) {
        cManager.addClient(c);
    }

    @Override
    public void onClockTick() {
        Event evt = evtBuilder.createTickEvent();
        evtPerformer.process(evt);
    }
    
}
