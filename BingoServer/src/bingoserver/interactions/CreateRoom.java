/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.models.UserCard;
import bingoserver.repositories.CardRepository;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserCardRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.CreatedRoomResponse;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class CreateRoom extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        CardRepository cardRepo = getRepositoryManager().getCardRepository();
        UserCardRepository ucRepo = getRepositoryManager().getUserCardRepository();
        UserRepository userRepo = getRepositoryManager().getUserRepository();

        User user = getSessionUser();

        if (roomRepo.findRoomByUser(user) != null) {
            throw new Exception("User already assigned to a room");
        }

        BingoCard card = cardRepo.getRandomCard();
        UserCard us = new UserCard(user, card);
        Room createdRoom = roomRepo.createRoom(us);

        /*if (card == null) {
            throw new Exception(">>>>>>>> BEWARE!!!!! No cards available");
        }*/ // NEVER WILL HAPPEN HU3

        ucRepo.addUserToRoomWithCard(user, createdRoom, card);

        getResponseManager().respondToUser(new CreatedRoomResponse(createdRoom.getId()), user);

        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));
    }

}
