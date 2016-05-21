/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.gameEvent.Event;
import bingoserver.gameEvent.EventBuilder;
import bingoserver.gameEvent.EventPerformer;
import bingoserver.network.Client;
import bingoserver.network.ClientListener;
import bingoserver.network.ClientReceiverListener;
import bingoserver.repositories.RepositoryManager;
import bingoserver.responses.ErrorResponse;
import requests.InteractionRequest;
import requests.Request;
import requests.RequestBuilder;

/**
 *
 * @author 15096134
 */
public class GameDelegate implements ClientListener,
        ClientReceiverListener,
        ClockListener {

    private RepositoryManager repositoryManager;
    private final ClientsManager cManager;

    private final RequestBuilder requestBuilder;

    private final EventPerformer evtPerformer;
    private final EventBuilder evtBuilder;

    public GameDelegate() {
        cManager = new ClientsManager();
        evtPerformer = new EventPerformer();
        evtBuilder = new EventBuilder(repositoryManager, cManager);
        requestBuilder = new RequestBuilder();
    }

    @Override
    public void onClientDisconnected(Client c) {
        cManager.removeClient(c);
    }

    @Override
    public void onClientMessage(Client client, String message) {
        Request request = requestBuilder.buildRequestForMessage(message);

        if (request.valid() && request instanceof InteractionRequest) {
            Event evt = evtBuilder.createClientEvent(client, (InteractionRequest) request);

            if (evt != null) {
                evtPerformer.process(evt);
            }
        } else {
            client.send(new ErrorResponse(request));
        }
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
