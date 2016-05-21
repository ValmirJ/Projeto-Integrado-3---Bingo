/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.parameters.ParamGroups;
import bingoserver.session.UserClientSession;

/**
 *
 * @author 15096134
 */
public abstract class UserInteractor extends Interactor {

    private UserClientSession userManager;

    public UserInteractor() {
        super();
    }

    public UserClientSession getUserClientSession() {
        return this.userManager;
    }

    public void setUserClientSession(UserClientSession userManager) {
        this.userManager = userManager;
    }

    public abstract void perform(ParamGroups params);
}
