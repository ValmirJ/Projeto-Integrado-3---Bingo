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
public class IntervalChange extends Interactor {

    @Override
    public void perform(JSONObject params) throws Exception {
        Integer time = toIntExact((Long) params.get("time"));
        getFormManager().getTelaJogo().setTime(time);
    }
    
}
