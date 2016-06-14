/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static java.lang.Math.toIntExact;

/**
 *
 * @author valmir
 */
public class Room {
    int id;
    ArrayList<User> users = new ArrayList<>();
    
    public Room(JSONObject obj) {
        this.id = toIntExact((Long)obj.get("id"));
        JSONArray jsonUsers = (JSONArray) obj.get("users");
        for(Object u : jsonUsers) {
            this.users.add(new User((JSONObject)u));
        }
    }
    
    public int getId() {
        return this.id;
    }
    public ArrayList<User> getUsers() {
        return this.users;
    }
    
    @Override
    public String toString() {
        return "Sala " + this.id;
    }
    
}
