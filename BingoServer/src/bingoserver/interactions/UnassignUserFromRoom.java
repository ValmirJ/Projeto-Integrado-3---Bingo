/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserCardRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.UserRemovedFromRoomResponse;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class UnassignUserFromRoom extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        UserCardRepository ucRepo = getRepositoryManager().getUserCardRepository();
        UserRepository userRepo = getRepositoryManager().getUserRepository();

        User user = getSessionUser();
        Room room = roomRepo.findRoomByUser(user);

        if (room == null) {
            throw new Exception("User not assigned in a room");
        }
        
        if (room.getState() != Room.RoomState.initialized) {
            // Ignore
            return;
        }

        List<User> users = roomRepo.usersInRoom(room);
        
        if (roomRepo.roomOwnedBy(user) != null) {
            roomRepo.removeRoom(room);
            getResponseManager().respondToUsers(new UserRemovedFromRoomResponse(), users);
        } else {
            ucRepo.removeUserFromRoom(user, room);

            getResponseManager().respondToUser(new UserRemovedFromRoomResponse(), user);

            List<User> usersInRoom = roomRepo.usersInRoom(room);

            if (usersInRoom.isEmpty()) {
                roomRepo.removeRoom(room);
            } else {
                getResponseManager().respondToUsers(new UsersInRoomChangedResponse(usersInRoom), usersInRoom);
            }
        }

        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));
    }

}
