/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import requests.Request;

/**
 *
 * @author 15096134
 */
public class ErrorResponse extends Response {

    private final Request request;

    public ErrorResponse(Request request) {
        this.request = request;
    }

    @Override
    public String responseData() {
        return "invalid_command<" + request.getRawData() + ">";
    }
}
