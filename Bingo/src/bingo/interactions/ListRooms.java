/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.FormManager;
import bingo.TelaSalas;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class ListRooms extends Interactor{

    private FormManager fm = this.getFormManager();
    private TelaSalas telaSalas = this.fm.getTelaSalas();
    
    public ListRooms() {
        super();
    }
    
    @Override
    public void perform(JSONObject params) throws Exception {
        
    }
    
}
