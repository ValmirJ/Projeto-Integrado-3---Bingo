/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

import bingoserver.models.Room;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class AvailableRoomsResponse extends Response {

    private final List<Room> rooms;

    public AvailableRoomsResponse(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String responseData() {

        return "";
    }

}
