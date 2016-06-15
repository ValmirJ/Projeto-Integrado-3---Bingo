/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class RemovedFromRoom extends Interactor {

    @Override
    public void perform(JSONObject params) throws Exception {
        getFormManager().getTelaSalaDeEspera().setVisible(false);
        getFormManager().getTelaSalas().setVisible(true);
    }
    
}
