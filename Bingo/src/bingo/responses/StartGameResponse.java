/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class StartGameResponse extends Response {

    @Override
    public JSONObject responseJson() {
        JSONObject json = new JSONObject();
        json.put("type", "iniciar-sala");
        return json;
    }
    
}
