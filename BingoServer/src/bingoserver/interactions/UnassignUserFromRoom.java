/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.parameters.ParamGroups;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserCardRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.UserRemovedFromRoomResponse;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class UnassignUserFromRoom extends UserInteractor {

    @Override
    public void perform(ParamGroups params) throws Exception {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        UserCardRepository ucRepo = null;
        UserRepository userRepo = null;

        User user = getSessionUser();
        Room room = roomRepo.findRoomByUser(user);

        if (room == null) {
            throw new Exception("User not assigned in a room");
        }

        ucRepo.removeUserFromRoom(user, room);

        getResponseManager().respondToUser(new UserRemovedFromRoomResponse(), user);

        List<User> usersInRoom = roomRepo.usersInRoom(room);
        getResponseManager().respondToUsers(new UsersInRoomChangedResponse(room, usersInRoom), usersInRoom);

        List<Room> rooms = roomRepo.currentOpenRooms();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom());
    }

}
