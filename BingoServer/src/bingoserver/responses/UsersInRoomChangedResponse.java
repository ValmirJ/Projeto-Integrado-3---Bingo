/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.models.User;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class UsersInRoomChangedResponse extends Response {

    private final List<User> users;

    public UsersInRoomChangedResponse(List<User> users) {
        if (users == null) {
            throw new NullPointerException("users cannot be null");
        }

        this.users = users;
    }

    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();

        obj.put("type", "usuarios-conectados-na-sua-sala");

        JSONArray usersArray = new JSONArray();

        for (User user : users) {
            usersArray.add(user.asJson());
        }

        obj.put("usuarios", usersArray);
        return obj;
    }

}
