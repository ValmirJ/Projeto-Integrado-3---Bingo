/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.parameters.ParamGroups;
import bingoserver.repositories.CardRepository;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserCardRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.UserAcceptedInRoom;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class AssignUserToRoom extends UserInteractor {

    @Override
    public void perform(ParamGroups params) throws Exception {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        CardRepository cardRepo = null;
        UserCardRepository ucRepo = null;
        UserRepository userRepo = null;

        User user = getSessionUser();

        if (roomRepo.findRoomByUser(user) != null) {
            throw new Exception("User already assigned to a room");
        }

        Room desiredRoom = roomRepo.findRoomById(params.getParamGroup(0).getParam(0));
        BingoCard card = cardRepo.getRandomCardAvailableForRoom(desiredRoom);

        if (card == null) {
            throw new Exception("No cards available to desired room");
            // TODO: Warn the user that the room is full
        }

        ucRepo.addUserToRoomWithCard(user, desiredRoom, card);

        getResponseManager().respondToUser(new UserAcceptedInRoom(), user);

        List<Room> rooms = roomRepo.currentOpenRooms();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom());

        List<User> usersInRoom = roomRepo.usersInRoom(desiredRoom);
        getResponseManager().respondToUsers(new UsersInRoomChangedResponse(desiredRoom, usersInRoom), usersInRoom);
    }
}
