/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.GameDelegate;
import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.CardRepository;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserCardRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.RoomIsFullResponse;
import bingoserver.responses.UserAcceptedInRoom;
import bingoserver.responses.UsersInRoomChangedResponse;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class AssignUserToRoom extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        Integer roomId = (Integer) params.get("id-sala");

        if (roomId == null) {
            throw new NullPointerException("Param id-sala inavlid!");
        }

        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        CardRepository cardRepo = getRepositoryManager().getCardRepository();
        UserCardRepository ucRepo = getRepositoryManager().getUserCardRepository();
        UserRepository userRepo = getRepositoryManager().getUserRepository();

        User user = getSessionUser();

        if (roomRepo.findRoomByUser(user) != null) {
            throw new Exception("User already assigned to a room");
        }

        Room desiredRoom = roomRepo.findRoomById(roomId);

        if (desiredRoom == null || desiredRoom.isStarted()) {
            throw new Exception("User tried to get in a started or unavalable room");
        }

        BingoCard card = cardRepo.getRandomCardAvailableForRoom(desiredRoom);

        if (card == null) {
            getResponseManager().respondToUser(new RoomIsFullResponse(), user);
            Logger.getLogger(GameDelegate.class.getName()).log(Level.INFO, "User tried to get in a full room. No more cards available.");
            return;
        }

        ucRepo.addUserToRoomWithCard(user, desiredRoom, card);

        getResponseManager().respondToUser(new UserAcceptedInRoom(), user);

        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom());

        List<User> usersInRoom = roomRepo.usersInRoom(desiredRoom);
        getResponseManager().respondToUsers(new UsersInRoomChangedResponse(usersInRoom), usersInRoom);
    }
}
