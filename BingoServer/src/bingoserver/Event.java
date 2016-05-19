/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import bingoserver.interactions.Interactor;
import models.User;

/**
 *
 * @author 15096134
 */
class Event {
    private final Interactor interactor;
    private final UserManager manager;
    
    private Event(Interactor i, UserManager cm) {
        interactor = i;
        manager = cm;
    }
    
    public void perform() {
        
    }
}
