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
public class YouLoseResponse extends Response {

    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();

        obj.put("type", "you-lose");

        return obj;
    }

}
