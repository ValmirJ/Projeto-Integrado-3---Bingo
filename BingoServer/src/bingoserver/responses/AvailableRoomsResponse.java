/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.models.Room;
import bingoserver.models.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class AvailableRoomsResponse extends Response {

    private final HashMap<Room, List<User>> rooms;

    public AvailableRoomsResponse(HashMap<Room, List<User>> rooms) {
        if (rooms == null) {
            throw new NullPointerException("rooms cannot be null");
        }

        this.rooms = rooms;
    }

    @Override
    public JSONObject responseJson() {
        JSONObject obj = new JSONObject();

        obj.put("type", "salas-disponiveis");
        JSONArray roomsJson = new JSONArray();

        for (Entry<Room, List<User>> entry : rooms.entrySet()) {
            Room room = entry.getKey();
            List<User> users = entry.getValue();

            JSONObject roomJson = new JSONObject();
            roomJson.put("id", room.getId());

            JSONArray usersArray = new JSONArray();

            for (User user : users) {
                usersArray.add(user.asJson());
            }

            roomJson.put("users", usersArray);
            roomsJson.add(roomJson);
        }

        obj.put("salas", roomsJson);
        return obj;
    }

}
