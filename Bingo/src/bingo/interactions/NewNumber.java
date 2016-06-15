/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import org.json.simple.JSONObject;
import static java.lang.Math.toIntExact;

/**
 *
 * @author guilherme
 */
public class NewNumber extends IntervalChange {

    @Override
    public void perform(JSONObject params) throws Exception {
        super.perform(params); 
        Integer n = toIntExact((Long) params.get("number"));
        
        getFormManager().getTelaJogo().addNextNumber(n);
    }
    
}
