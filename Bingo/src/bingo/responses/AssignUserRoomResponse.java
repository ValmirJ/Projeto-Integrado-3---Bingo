/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.responses;

import org.json.simple.JSONObject;

/**
 *
 * @author valmir
 */
public class AssignUserRoomResponse extends Response {

    int idSala;
    
    public AssignUserRoomResponse(int idSala) {
        this.idSala = idSala;
    }
    
    @Override
    public JSONObject responseJson() {
        //send id of room
       JSONObject obj = new JSONObject();
       obj.put("type", "entrar-na-sala");
       obj.put("id-sala", idSala);
       
       return obj;
    }
    
}
