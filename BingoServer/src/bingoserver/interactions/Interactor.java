/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.network.ResponseManager;
import bingoserver.repositories.RepositoryManager;

/**
 *
 * @author 15096134
 */
public abstract class Interactor {

    private RepositoryManager repositoryManager;
    private ResponseManager responseManager;

    public ResponseManager getResponseManager() {
        return responseManager;
    }

    public void setResponseManager(ResponseManager responseManager) {
        this.responseManager = responseManager;
    }

    public void setRepositoryManager(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }

    public Interactor() {
    }
}
