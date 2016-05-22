/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.session;

import bingoserver.models.User;
import bingoserver.network.Client;

/**
 *
 * @author 15096134
 */
public class UserClientSession {

    private final Client client;
    private final User user;

    public UserClientSession(UserClientSession other) {
        if (other == null) {
            throw new NullPointerException("UserClientSession cannot be null");
        }

        this.user = other.user;
        this.client = new Client(other.client);
    }

    public UserClientSession(Client client, User user) {
        this.client = new Client(client);
        this.user = user;
    }

    public Client getClient() {
        return new Client(client);
    }

    public User getUser() {
        return user;
    }
}
