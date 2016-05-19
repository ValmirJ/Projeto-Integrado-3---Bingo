/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.messages.Message;
import bingoserver.network.Client;
import bingoserver.interactions.Interactor;
import bingoserver.interactions.TimerInteractor;
import bingoserver.interactions.UserInteractor;
import bingoserver.messages.MessageParameters;
import bingoserver.repositories.RepositoryManager;
import bingoserver.responses.ErrorResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
class EventBuilder {
    private final RepositoryManager repoManager;
    private final ClientsManager clientsManager;
    
    EventBuilder(RepositoryManager repoManager, ClientsManager clientsManager) {
        this.repoManager = repoManager;
        this.clientsManager = clientsManager;
    }

    Event createClientEvent(final Client c, final Message m) {
        final UserManager um = clientsManager.getUserManager(c);
        final MessageParameters mp = m.getParameters();
        
        ResponseManager respMgr = clientsManager.getResponseManager();
        Class<? extends Interactor> interactorClass = m.getInteractorClass();
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
            c.send(new ErrorResponse(m));
            return null;
        }
        
        return new Event(newInstance) {
            @Override
            public void run() {
                ((UserInteractor) getInteractor()).perform(mp, um);
            }
        };
    }

    Event createTickEvent() {
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
