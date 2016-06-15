/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static java.lang.Math.toIntExact;
import models.Card;

/**
 *
 * @author guilherme
 */
public class StartGame extends Interactor {

    @Override
    public void perform(JSONObject params) throws Exception {
        Card card = new Card();
        JSONArray cardNumbers = (JSONArray) params.get("card");
        
        for (Object pairObj : cardNumbers) {
            JSONObject pair = (JSONObject) pairObj;
            
            Integer row = toIntExact((Long) pair.get("row"));
            Integer col = toIntExact((Long) pair.get("col"));
            Integer num = toIntExact((Long) pair.get("value"));
            
            card.setNumber(row, col, num);
        }
        
        getFormManager().getTelaJogo().setCard(card);
        getFormManager().getTelaJogo().setVisible(true);
        getFormManager().getTelaSalaDeEspera().setVisible(false);
    }
    
}
