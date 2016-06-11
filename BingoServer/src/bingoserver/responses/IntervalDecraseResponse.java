/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author 15096134
 */
public class IntervalDecraseResponse extends TimeProgressResponse {
    
    public IntervalDecraseResponse(int time) {
        super(time);
    }

    @Override
    public JSONObject responseJson() {
        JSONObject base = super.responseJson();
        base.put("type", "interval-decrase");
        return base; 
    }
}
