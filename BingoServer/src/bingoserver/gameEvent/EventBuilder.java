/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.gameEvent;

import bingoserver.ClientsManager;
import bingoserver.ResponseManager;
import bingoserver.UserManager;
import bingoserver.interactions.Interactor;
import bingoserver.interactions.TimerInteractor;
import bingoserver.interactions.UserInteractor;
import bingoserver.network.Client;
import bingoserver.repositories.RepositoryManager;
import bingoserver.responses.ErrorResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingoserver.requests.InteractionRequest;
import bingoserver.parameters.ParamGroups;

/**
 *
 * @author 15096134
 */
public class EventBuilder {

    private final RepositoryManager repoManager;
    private final ClientsManager clientsManager;

    public EventBuilder(RepositoryManager repoManager, ClientsManager clientsManager) {
        this.repoManager = repoManager;
        this.clientsManager = clientsManager;
    }

    public Event createClientEvent(final Client c, final InteractionRequest request) {
        final UserManager userManager = clientsManager.getUserManager(c);
        final ParamGroups params = request.getParams();

        ResponseManager respMgr = clientsManager.getResponseManager();
        Class<? extends Interactor> interactorClass = request.getInteractorClass();
        Interactor newInstance = null;

        try {
            newInstance = interactorClass.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(EventBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EventBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (newInstance != null) {
            newInstance.setRepositoryManager(repoManager);
            newInstance.setResponseManager(respMgr);
        } else {
            Logger.getLogger(EventBuilder.class.getName()).log(Level.SEVERE, null,
                    "Failed to create interactor " + interactorClass.getCanonicalName() + " for request " + request.getRawData());
            c.send(new ErrorResponse(request));
            return null;
        }

        return new Event(newInstance) {
            @Override
            public void run() {
                ((UserInteractor) getInteractor()).perform(params, userManager);
            }
        };
    }

    public Event createTickEvent() {
        ResponseManager respMgr = clientsManager.getResponseManager();
        TimerInteractor newInstance = new TimerInteractor();

        newInstance.setRepositoryManager(repoManager);
        newInstance.setResponseManager(respMgr);

        return new Event(newInstance) {
            @Override
            public void run() {
                ((TimerInteractor) getInteractor()).perform();
            }
        };
    }

}
