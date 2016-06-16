/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.MyJFrame;
import bingo.Tela;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class DisplayGenericError extends Interactor {

    private Tela tela;
    
    @Override
    public void perform(JSONObject params) throws Exception {
        this.tela = this.getFormManager().getCurrentForm();
        if(tela != null) {
            tela.showGenericError();
            ((MyJFrame)this.tela).hideLoaders();
        }
    }
    
}
