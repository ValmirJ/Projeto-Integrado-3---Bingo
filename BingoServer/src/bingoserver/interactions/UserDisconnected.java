/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.EverybodyHasGoneResponse;
import bingoserver.responses.UserRemovedFromRoomResponse;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.HashMap;
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

        UserRepository userRepo = getRepositoryManager().getUserRepository();
        userRepo.removeUser(user);
        
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        Room room = roomRepo.findRoomByUser(user);

        if (room == null) {
            return;
        }

        boolean isOwner = roomRepo.roomOwnedBy(user) != null;
        getRepositoryManager().getUserCardRepository().removeUserFromRoom(user, room);
        List<User> users = roomRepo.usersInRoom(room);

        if (room.getState() == Room.RoomState.initialized) {
            if (users.isEmpty()) {
                roomRepo.removeRoom(room);
            } else {
                if (isOwner) {
                    roomRepo.removeRoom(room);
                    getResponseManager().respondToUsers(new UserRemovedFromRoomResponse(), users);
                } else {
                    getResponseManager().respondToUsers(new UsersInRoomChangedResponse(users), users);
                }
            }
            
            HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
            getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));   
            
            return;
        }
        
        if (users.size() < Settings.MINIMUM_USERS_IN_ROOM) {
            roomRepo.removeRoom(room);
            getResponseManager().respondToUsers(new EverybodyHasGoneResponse(), users);
            
            for (User userToHold : users)
                userRepo.holdUser(userToHold);
        }
    }
}
