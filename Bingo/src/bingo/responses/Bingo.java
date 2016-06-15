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
public class Bingo extends Response {

    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();
        obj.put("type", "bingo");
        return obj;
    }
    
}
