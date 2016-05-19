/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.network.Client;
import bingoserver.responses.Response;
import java.util.HashMap;
import java.util.Map.Entry;
import models.User;

/**
 *
 * @author 15096134
 */
public class ClientsManager {
    private final HashMap<Client, User> assigns = new HashMap<>();
    
    private final ResponseManager responseManager = new ResponseManager() {
        @Override
        public void respondToUser(Response resp, User u) {
            ClientsManager.this.respondToUser(resp, u);
        }

        @Override
        public void respondToUsers(Response resp, User[] users) {
            ClientsManager.this.respondToUsers(resp, users);
        }
    };
    
    public void addClient(Client c) {
        assigns.put(c, null);
    }
    
    public void removeClient(Client c) {
        assigns.remove(c);
    }
    
    public void setClientUser(Client c, User u) {
        assigns.replace(c, u);
    }
    
    public User getClientUser(Client c) {
        return assigns.getOrDefault(c, null);
    }
    
    public void respondToUser(Response resp, User u) {
        for (Entry<Client, User> e : assigns.entrySet()) {
            if (e.getValue().equals(u)) {
                e.getKey().send(resp);
            }
        }
    }
    
    public void respondToUsers(Response resp, User[] users) {
        for (User u : users) {
            respondToUser(resp, u);
        }
    }
    
    public UserManager getUserManager(final Client c) {
        return new UserManager() {
            @Override
            public void setUser(User u) {
                setClientUser(c, u);
            }

            @Override
            public User getUser() {
                return getClientUser(c);
            }
        };
    }
    
    public ResponseManager getResponseManager() {
        return responseManager;
    }
}
