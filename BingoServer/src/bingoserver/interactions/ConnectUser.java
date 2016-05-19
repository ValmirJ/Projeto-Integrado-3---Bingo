/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.ClientsManager;
import bingoserver.ResponseManager;
import bingoserver.repositories.RepositoryManager;
import models.User;
import bingoserver.UserManager;

/**
 *
 * @author 15096134
 */
public class ConnectUser extends UserInteractor {

    public ConnectUser(RepositoryManager mgr, ResponseManager responseManager) {
        super(mgr, responseManager);
    }

    
    @Override
    public void perform(UserManager mgr) {
        
    }
}
