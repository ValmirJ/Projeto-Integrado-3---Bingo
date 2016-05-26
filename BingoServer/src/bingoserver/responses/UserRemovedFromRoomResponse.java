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
public class UserRemovedFromRoomResponse extends Response {

    @Override
    public String responseData() {
        return "removido_da_sala";
    }

}
