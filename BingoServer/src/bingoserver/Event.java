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
abstract class Event implements Runnable {
    private final Interactor interactor;

    public Interactor getInteractor() {
        return interactor;
    }
    
    public Event(Interactor i) {
        interactor = i;
    }
}
