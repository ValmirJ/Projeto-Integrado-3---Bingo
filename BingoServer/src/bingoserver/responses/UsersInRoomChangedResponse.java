/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.models.Room;
import bingoserver.models.User;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class UsersInRoomChangedResponse extends Response {

    public UsersInRoomChangedResponse(Room room, List<User> users) {

    }

    @Override
    public String responseData() {
        return "";
    }

}
