/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.gameEvent;

import bingoserver.interactions.Interactor;

/**
 *
 * @author 15096134
 */
public abstract class Event implements Runnable {

    private final Interactor interactor;

    public Interactor getInteractor() {
        return interactor;
    }

    protected Event(Interactor i) {
        interactor = i;
    }
}
