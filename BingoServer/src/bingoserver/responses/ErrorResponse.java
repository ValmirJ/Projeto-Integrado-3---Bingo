/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.requests.Request;
import org.json.simple.JSONObject;

/**
 *
 * @author 15096134
 */
public class ErrorResponse extends Response {

    private final Request request;

    public ErrorResponse(Request request) {
        if (request == null) {
            throw new NullPointerException("request cannot be null");
        }

        this.request = request;
    }

    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();
        obj.put("type", "erro-ao-processar-comando");
        obj.put("erro", true);
        obj.put("comando-recebido", request.getRawData());

        return obj;
    }
}
