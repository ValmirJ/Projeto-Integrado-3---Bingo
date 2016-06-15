/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.SalaDeEspera;
import java.util.ArrayList;
import models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class UpdateListUsersInRoom extends Interactor {

    private SalaDeEspera telaSalaDeEspera;
    ArrayList<User> users = new ArrayList<>();
    
    @Override
    public void perform(JSONObject params) throws Exception {
        this.telaSalaDeEspera = this.getFormManager().getTelaSalaDeEspera();
        
        JSONArray jsonUsers = (JSONArray) params.get("usuarios");
        
        for(Object j: jsonUsers) {
            users.add(new User((JSONObject)(j)));
        }
        this.telaSalaDeEspera.updateListUsers(users);
    }
    
}
