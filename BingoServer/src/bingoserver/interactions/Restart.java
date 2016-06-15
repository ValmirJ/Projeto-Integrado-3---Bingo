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
import bingoserver.responses.UserConnectedResponse;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class Restart extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        UserRepository userRepo = getRepositoryManager().getUserRepository();
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        
        userRepo.unholdUser(getSessionUser());
        getResponseManager().respondToUser(new UserConnectedResponse(), getSessionUser());

        // Envia para o client as salas disponíveis,
        // bem como os usuários que estão nelas.
        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));
    }
    
}
