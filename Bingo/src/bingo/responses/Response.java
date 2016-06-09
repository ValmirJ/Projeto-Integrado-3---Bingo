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
public abstract class Response {
    
    public abstract JSONObject responseJson();

    public String responseData() {
        return responseJson().toJSONString();
    }
}
