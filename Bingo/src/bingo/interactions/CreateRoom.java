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
public class CreateRoom extends Interactor {
    
    private TelaSalas telaSalas;
    private SalaDeEspera telaSalaDeEspera;
    
    @Override
    public void perform(JSONObject params) throws Exception {
        this.telaSalas = this.getFormManager().getTelaSalas();
        this.telaSalaDeEspera = this.getFormManager().getTelaSalaDeEspera();
        
        this.telaSalas.setVisible(false);
        this.telaSalaDeEspera.setVisible(true);  
        this.telaSalaDeEspera.setEnabledStartButtonToOwner();
    }
    
}
