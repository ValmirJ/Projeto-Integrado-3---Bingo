/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import models.User;

/**
 *
 * @author 15096134
 */
public interface UserManager {
    public abstract User getUser();
    public abstract void setUser(User u);
}
