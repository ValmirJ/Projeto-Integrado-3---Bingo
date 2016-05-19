/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.messages.Message;

/**
 *
 * @author 15096134
 */
public class ErrorResponse extends Response {
    private final Message m;
    
    public ErrorResponse(Message m) {
        this.m = m;
    }

    @Override
    public String responseData() {
        return "invalid_command<" + m.getMessage() + ">";
    }   
}
