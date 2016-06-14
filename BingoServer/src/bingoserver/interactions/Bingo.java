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
import bingoserver.responses.BingoWinnedResponse;
import bingoserver.responses.InvalidBingo;
import bingoserver.responses.YouLoseResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 15096134
 */
public class Bingo extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        List<Integer> numbers = new ArrayList<>();

        JSONArray numbersJson = (JSONArray) params.get("numbers");
        for (Object numberObj : numbersJson) {
            Integer number = (Integer) numberObj;
            if (number != null) {
                numbers.add(number);
            }
        }
        
        User user = getSessionUser();
        
        if (numbers.size() < Settings.MINIMUM_CORRECT_NUMBERS) {
            getResponseManager().respondToUser(new InvalidBingo(), user);
            return;
        }
        
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        UserRepository userRepo = getRepositoryManager().getUserRepository();
        
        Room room = roomRepo.findRoomByUser(user);
        List<Integer> sorteds = room.getSortedNumbers();
        
        for (Integer i : numbers) {
            if (!sorteds.contains(i)) {
                getResponseManager().respondToUser(new InvalidBingo(), user);
                return;
            }
        }
        
        List<User> users = roomRepo.usersInRoom(room);
        roomRepo.removeRoom(room);
        
        getResponseManager().respondToUser(new BingoWinnedResponse(), user);
        
        for (User looser : users) {
            if (!looser.equals(user)) {
                getResponseManager().respondToUser(new YouLoseResponse(), user);
            }
        }
        
        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));
    }
}
