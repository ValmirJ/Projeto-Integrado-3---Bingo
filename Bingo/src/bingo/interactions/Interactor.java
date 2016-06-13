/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.FormManager;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public abstract class Interactor {
    private FormManager formManager;
    
    public void setFormManager(FormManager fm) {
        this.formManager = fm;
    }
    
    public FormManager getFormManager() {
        return this.formManager;
    }
    
    public Interactor() {
        
    }
    
    public abstract void perform(JSONObject params) throws Exception;
}
