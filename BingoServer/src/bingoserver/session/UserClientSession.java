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

    public UserClientSession(Client client, User user) {
        this.client = client;
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public User getUser() {
        return user;
    }
}
