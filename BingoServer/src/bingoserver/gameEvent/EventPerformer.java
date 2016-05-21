/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.gameEvent;

/**
 *
 * @author 15096134
 */
public class EventPerformer {

    public void process(Event evt) {
        evt.run();
    }
}
