/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import bingoserver.ResponseManager;
import bingoserver.models.User;
import bingoserver.responses.Response;
import bingoserver.session.UserClientSession;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author 15096134
 */
public class ClientsManager implements ResponseManager {

    private final HashMap<Client, User> assigns = new HashMap<>();

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

    @Override
    public void respondToUser(Response resp, User u) {
        for (Entry<Client, User> e : assigns.entrySet()) {
            if (e.getValue().equals(u)) {
                e.getKey().send(resp);
            }
        }
    }

    @Override
    public void respondToUsers(Response resp, User[] users) {
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

    public UserClientSession getUserClientSession(final Client client) {
        return new UserClientSession(client, getClientUser(client));
    }

    public void setUserClientSession(UserClientSession session) {
        setClientUser(session.getClient(), session.getUser());
    }

    public ResponseManager getResponseManager() {
        return this;
    }

    public int getClientCount() {
        return assigns.size();
    }
}
