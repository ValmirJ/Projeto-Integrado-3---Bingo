/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.BingoServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class TimerInteractor extends Interactor {

    public TimerInteractor() {
        super();
    }

    public void perform() {
        Logger.getLogger(BingoServer.class.getName()).log(Level.INFO, "Clock Tick");
    }
}
