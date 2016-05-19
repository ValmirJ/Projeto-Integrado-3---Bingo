/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.ResponseManager;
import bingoserver.repositories.RepositoryManager;
import models.User;
import bingoserver.UserManager;

/**
 *
 * @author 15096134
 */
public abstract class Interactor {
    private RepositoryManager repositoryManager;
    private ResponseManager responseManager;

    protected RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
    
    public Interactor(RepositoryManager mgr, ResponseManager responseManager) {
        this.repositoryManager = mgr;
        this.responseManager = responseManager;
    }
}
