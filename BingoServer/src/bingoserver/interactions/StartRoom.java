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
import bingoserver.repositories.RoomRepository;
import bingoserver.responses.GameStartResponse;
import bingoserver.responses.Response;
import bingoserver.responses.TooLessUsersInRoomResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author 15096134
 */
public class StartRoom extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();

        User requester = getSessionUser();
        Room owned = roomRepo.roomOwnedBy(requester);

        if (owned == null) {
            throw new Exception("Room not owned");
        }

        List<User> users = roomRepo.usersInRoom(owned);

        if (users.size() < Settings.MINIMUM_USERS_IN_ROOM) {
            getResponseManager().respondToUser(new TooLessUsersInRoomResponse(), requester);
            return;
        }

        roomRepo.startRoom(owned);

        for (User user : users) {
            BingoCard card = roomRepo.getCardFor(user, owned);

            if (card != null) {
                Response resp = new GameStartResponse(card);
                getResponseManager().respondToUsers(resp, user);
            } else {
                Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, "Found user without card!");
            }
        }
    }
}
