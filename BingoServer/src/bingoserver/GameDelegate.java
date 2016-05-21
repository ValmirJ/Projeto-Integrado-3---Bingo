/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.interactions.Interactor;
import bingoserver.interactions.TimerInteractor;
import bingoserver.interactions.UserInteractor;
import bingoserver.network.Client;
import bingoserver.network.ClientListener;
import bingoserver.network.ClientReceiverListener;
import bingoserver.network.ClientsManager;
import bingoserver.repositories.RepositoryManager;
import bingoserver.requests.InteractionRequest;
import bingoserver.requests.Request;
import bingoserver.requests.RequestBuilder;
import bingoserver.responses.ErrorResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class GameDelegate implements ClientListener,
        ClientReceiverListener,
        ClockListener {

    private final RepositoryManager repositoryManager;
    private final ClientsManager clientsManager;
    private final RequestBuilder requestBuilder;

    public GameDelegate() {
        repositoryManager = new RepositoryManager();
        clientsManager = new ClientsManager();
        requestBuilder = new RequestBuilder();
    }

    @Override
    public void onClientDisconnected(Client c) {
        clientsManager.removeClient(c);
    }

    @Override
    public void onClientMessage(Client client, String message) {
        Request request = requestBuilder.buildRequestForMessage(message);

        if (request.valid() && request instanceof InteractionRequest) {
            InteractionRequest interactionReq = (InteractionRequest) request;

            try {
                UserInteractor ui = interactionReq.getInteractorClass().newInstance();
                setInteractorMamagers(ui);

                ui.setUserClientSession(clientsManager.getUserClientSession(client));
                ui.perform(interactionReq.getParams());
                clientsManager.setUserClientSession(ui.getUserClientSession());
            } catch (InstantiationException ex) {
                Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
            }

            client.read();
        } else {
            client.send(new ErrorResponse(request));
        }
    }

    @Override
    public void onClientConnected(Client c) {
        clientsManager.addClient(c);
        c.read();
    }

    @Override
    public void onClockTick() {
        TimerInteractor timerInteractor = new TimerInteractor();
        setInteractorMamagers(timerInteractor);
        timerInteractor.perform();
    }

    private void setInteractorMamagers(Interactor i) {
        i.setRepositoryManager(repositoryManager);
        i.setResponseManager(clientsManager);
    }

}
