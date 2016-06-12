/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class NumberRefusedResponse extends Response {

    private final Integer number;

    public NumberRefusedResponse(Integer number) {
        // Beware! number CAN BE NULL!
        this.number = number;
    }

    @Override
    public JSONObject responseJson() {
        JSONObject response = new JSONObject();

        response.put("type", "number-refused");
        response.put("number", number);

        return response;
    }

}
