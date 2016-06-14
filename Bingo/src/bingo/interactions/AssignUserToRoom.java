/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.SalaDeEspera;
import bingo.TelaSalas;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class AssignUserToRoom extends Interactor {

    private TelaSalas telaSalas;
    private SalaDeEspera telaSalaDeEspera;
    
    @Override
    public void perform(JSONObject params) throws Exception {
        this.telaSalas = this.getFormManager().getTelaSalas();
        String typeResponse = (String)params.get("type");
        if(typeResponse.equals("sala-cheia")) {
            this.telaSalas.showRoomIsFull();
        }
        else {
            if(typeResponse.equals("aceito-na-sala")) {
                this.telaSalaDeEspera = this.getFormManager().getTelaSalaDeEspera();
                this.telaSalas.setVisible(false);
                this.telaSalaDeEspera.setVisible(true);
            }
        }
            
        
    }
    
}
