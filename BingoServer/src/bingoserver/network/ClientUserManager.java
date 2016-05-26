/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import bingoserver.models.User;
import bingoserver.responses.Response;
import bingoserver.session.SessionManager;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author 15096134
 */
public class ClientUserManager implements ResponseManager, SessionManager {

    private final AbstractMap<Client, User> assigns = new ConcurrentHashMap<>();

    private class NoUser extends User {

        public NoUser() {
            super(0, null);
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof NoUser;
        }

        @Override
        public int hashCode() {
            return super.hashCode() * 23;
        }
    };

    public void addClient(Client c) {
        assigns.put(c, new NoUser());
    }

    public boolean removeClient(Client c) {
        return assigns.remove(c) != null;
    }

    @Override
    public void setClientUser(Client c, User u) {
        assigns.replace(c, u);
    }

    @Override
    public User getClientUser(Client c) {
        User getted = assigns.getOrDefault(c, null);

        if (getted instanceof NoUser) {
            return null;
        }

        return getted;
    }

    @Override
    public void respondToUser(Response resp, User u) {
        Set<Entry<Client, User>> entries = assigns.entrySet();

        for (Entry<Client, User> e : entries) {
            if (e.getValue().equals(u)) {
                respondToClient(resp, e.getKey());
            }
        }
    }

    @Override
    public void respondToUsers(Response resp, User... users) {
        for (User u : users) {
            respondToUser(resp, u);
        }
    }

    @Override
    public void respondToUsers(Response resp, List<User> users) {
        for (User u : users) {
            respondToUser(resp, u);
        }
    }

    @Override
    public void respondToClient(Response resp, Client client) {
        if (assigns.containsKey(client)) {
            client.send(resp);
        }
    }
}
