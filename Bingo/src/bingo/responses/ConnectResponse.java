/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class ConnectResponse extends Response{
    private String ra;
    
    public ConnectResponse(String ra) {
        this.ra = ra;
    }
    
    @Override
    public JSONObject responseJson() {
        
        JSONObject obj = new JSONObject();
        obj.put("type", "conectar-com-ra");
        obj.put("ra", this.ra);

        return obj;
    }

    
}
