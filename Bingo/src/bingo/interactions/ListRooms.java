/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interactions;

import bingo.FormManager;
import bingo.TelaSalas;
import java.util.ArrayList;
import models.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class ListRooms extends Interactor{

    private TelaSalas telaSalas;
    ArrayList<Room> rooms = new ArrayList<>();
    
    public ListRooms() {
        super();
    }
    
    @Override
    public void perform(JSONObject params) throws Exception {
        this.telaSalas = this.getFormManager().getTelaSalas();
        JSONArray jsonRooms = (JSONArray) params.get("salas");
        for(Object r : jsonRooms) {
            this.rooms.add(new Room((JSONObject) r));
        } 
        this.telaSalas.addItensToAvailableRooms(rooms);
    }
    
    
}
