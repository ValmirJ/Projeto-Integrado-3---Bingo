/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.User;
import bingoserver.network.Client;
import bingoserver.parameters.ParamGroups;
import bingoserver.session.SessionManager;

/**
 *
 * @author 15096134
 */
public abstract class UserInteractor extends Interactor {

    private SessionManager sessionManager;
    private Client currentClient;

    public UserInteractor() {
        super();
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    User getSessionUser() {
        return getSessionManager().getClientUser(currentClient);
    }

    void setSessionUser(User user) {
        getSessionManager().setClientUser(currentClient, user);
    }

    public abstract void perform(ParamGroups params) throws Exception;
}
