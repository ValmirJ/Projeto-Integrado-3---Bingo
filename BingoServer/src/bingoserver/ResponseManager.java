/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.responses.Response;
import models.User;

/**
 *
 * @author 15096134
 */
public interface ResponseManager {
    public void respondToUser(Response resp, User u);        
    public void respondToUsers(Response resp, User[] users);
}
