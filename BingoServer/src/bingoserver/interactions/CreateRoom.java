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
import bingoserver.responses.CreatedRoomResponse;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class CreateRoom extends UserInteractor {

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

        Room createdRoom = roomRepo.createRoom();
        BingoCard card = cardRepo.getRandomCard();

        if (card == null) {
            throw new Exception("No cards available");
        }

        ucRepo.addUserToRoomWithCard(user, createdRoom, card);

        getResponseManager().respondToUser(new CreatedRoomResponse(createdRoom.getId()), user);

        List<Room> rooms = roomRepo.currentOpenRooms();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom());
    }

}
