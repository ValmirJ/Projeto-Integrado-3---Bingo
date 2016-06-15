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
public class EverybodyHasGoneResponse extends Response {

    @Override
    public JSONObject responseJson() {
        JSONObject o = new JSONObject();
        o.put("type", "everybody-has-gone");
        return o;
    }
    
}
