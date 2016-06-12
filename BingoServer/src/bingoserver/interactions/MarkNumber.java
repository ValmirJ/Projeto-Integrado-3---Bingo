/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.repositories.RoomRepository;
import bingoserver.responses.NumberRefusedResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author guilherme
 */
public class MarkNumber extends UserInteractor {

    @Override
    public void perform(JSONObject params) throws Exception {
        Integer number;

        try {
            number = (Integer) params.get("number");
        } catch (ClassCastException ex) {
            number = null;
        }

        if (number == null) {
            getResponseManager().respondToUser(new NumberRefusedResponse(number), getSessionUser());
            return;
        }

        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        Room room = roomRepo.findRoomByUser(getSessionUser());

        if (room == null) {
            throw new Exception("User without room tried to mark a number");
        }

        if (room.getSortedNumbers().contains(number)) {
            // TODO
        } else {
            getResponseManager().respondToUser(new NumberRefusedResponse(number), getSessionUser());
        }
    }

}
