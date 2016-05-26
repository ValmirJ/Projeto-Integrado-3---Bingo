/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.responses;

/**
 *
 * @author guilherme
 */
public class CreatedRoomResponse extends Response {

    private int roomId;

    public CreatedRoomResponse(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String responseData() {
        return "sala<" + roomId + ">criada";
    }

}
