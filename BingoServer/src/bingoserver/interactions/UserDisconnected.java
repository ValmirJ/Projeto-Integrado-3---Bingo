/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.RoomRepository;
import bingoserver.responses.TooLessUsersInRoomResponse;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class UserDisconnected extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        User user = getSessionUser();

        if (user == null) {
            return;
        }

        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();

        Room room = roomRepo.findRoomByUser(user);

        if (room == null) {
            return;
        }

        List<User> users = roomRepo.usersInRoom(room);

        if (room.getState() != Room.RoomState.initialized
                && users.size() < Settings.MINIMUM_USERS_IN_ROOM) {
            roomRepo.removeRoom(room);
            getResponseManager().respondToUsers(new TooLessUsersInRoomResponse(), users);
            return;
        }

        if (room.getState() == Room.RoomState.initialized) {
            getResponseManager().respondToUsers(new UsersInRoomChangedResponse(users), users);
        }
    }

}
