/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.ResponseManager;
import bingoserver.UserManager;
import bingoserver.messages.MessageParameters;
import bingoserver.repositories.RepositoryManager;

/**
 *
 * @author 15096134
 */
public abstract class UserInteractor extends Interactor{

    public UserInteractor() {
        super();
    }
    
    public abstract void perform(MessageParameters params, UserManager mgr);
}
