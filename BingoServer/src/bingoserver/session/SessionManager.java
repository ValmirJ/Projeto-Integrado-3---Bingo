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
 * @author guilherme
 */
public interface SessionManager {

    public void setClientUser(Client c, User u);

    public User getClientUser(Client c);
}
