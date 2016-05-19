/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.messages;

import bingoserver.interactions.ConnectUser;
import bingoserver.interactions.Interactor;

/**
 *
 * @author 15096134
 */
public class Message {
    private String message;
    
    public Message(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public MessageParameters getParameters() {
        return new MessageParameters(this);
    }
    
    public Class<? extends Interactor> getInteractorClass() {
        return ConnectUser.class;
    }
}
