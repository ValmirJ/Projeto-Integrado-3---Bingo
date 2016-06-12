/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.interactions.Interactor;
import bingoserver.interactions.TimerInteractor;
import bingoserver.interactions.UserDisconnected;
import bingoserver.interactions.UserInteractor;
import bingoserver.network.Client;
import bingoserver.network.ClientUserManager;
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
public class GameDelegate {

    private final RepositoryManager repositoryManager;
    private final ClientUserManager clientsManager;
    private final RequestBuilder requestBuilder;

    public GameDelegate(RepositoryManager repositoryManager, ClientUserManager clientsManager) {
        this.repositoryManager = repositoryManager;
        this.clientsManager = clientsManager;
        requestBuilder = new RequestBuilder();
    }

    public void onClientConnected(Client c) {
        clientsManager.addClient(c);
    }

    public void onClientDisconnected(Client client) {
        UserInteractor disc = new UserDisconnected();
        setInteractorManagers(disc);
        disc.setCurrentClient(client);
        disc.setSessionManager(clientsManager);

        try {
            disc.perform(null);
        } catch (Exception ex) {
            Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }

        clientsManager.removeClient(client);
    }

    public void onClientMessage(Client client, String message) {
        Request request = requestBuilder.buildRequestForMessage(message);

        if (request.valid() && request instanceof InteractionRequest) {
            InteractionRequest interactionReq = (InteractionRequest) request;
            Class<? extends UserInteractor> interactionClass = interactionReq.getInteractorClass();

            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Executing {0} interaction", interactionClass.getCanonicalName());

            try {
                UserInteractor ui = interactionClass.newInstance();
                setInteractorManagers(ui);
                ui.setCurrentClient(client);
                ui.setSessionManager(clientsManager);

                try {
                    ui.perform(interactionReq.getRequestJson());
                } catch (Exception ex) {
                    Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
                    sendErrorResponse(request, client);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
                sendErrorResponse(request, client);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
                sendErrorResponse(request, client);
            }
        } else {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Invalid request {0}", message);
            sendErrorResponse(request, client);
        }
    }

    public void onClockTick() {
        TimerInteractor timerInteractor = new TimerInteractor();
        setInteractorManagers(timerInteractor);

        try {
            timerInteractor.perform();
        } catch (Exception ex) {
            Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setInteractorManagers(Interactor i) {
        i.setRepositoryManager(repositoryManager);
        i.setResponseManager(clientsManager);
    }

    private void sendErrorResponse(Request request, Client client) {
        ErrorResponse resp = new ErrorResponse(request);
        clientsManager.respondToClient(resp, client);
    }
}
