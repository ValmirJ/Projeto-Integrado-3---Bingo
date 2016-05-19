/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.repositories.RepositoryManager;
import models.User;

/**
 *
 * @author 15096134
 */
class EventBuilder {
    private RepositoryManager repoManager;
    private ClientsManager clientsManager;
    
    EventBuilder(RepositoryManager repoManager, ClientsManager clientsManager) {
        this.repoManager = repoManager;
        this.clientsManager = clientsManager;
    }

    Event createClientEvent(Client c, Message m) {
        UserManager um = clientsManager.getUserManager(c);
        ResponseManager respMgr = clientsManager.getResponseManager();
        return null;
    }

    Event createTickEvent() {
        ResponseManager respMgr = clientsManager.getResponseManager();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
