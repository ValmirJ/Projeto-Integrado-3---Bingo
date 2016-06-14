/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class User {
    private String ra;
    
    public User(JSONObject obj) {
        this.ra = (String) obj.get("ra");
    }
    
    public String getRa() {
        return this.ra;
    }
    
    @Override
    public String toString() {
        return "RA " + this.ra;
    }
}
    
