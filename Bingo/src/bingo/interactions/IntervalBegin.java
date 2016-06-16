/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.TelaJogo;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class IntervalBegin extends Interactor {

    private TelaJogo telaJogo;
    @Override
    public void perform(JSONObject params) throws Exception {
        this.telaJogo = this.getFormManager().getTelaJogo();
        this.telaJogo.intervalToFindNumbers();
    }
    
}
