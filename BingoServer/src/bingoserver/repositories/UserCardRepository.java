/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.models.UserCard;

/**
 *
 * @author guilherme
 */
public class UserCardRepository {

    public void addUserToRoomWithCard(User user, Room room, BingoCard card) throws Exception {
        addUserToRoomWithCard(user, room, card, false);
    }

    public void removeUserFromRoom(User user, Room room) {
        room.removeUserCardByUser(user);
    }

    public void addUserToRoomWithCard(User user, Room room, BingoCard card, boolean isOwner) throws Exception {
        if(room == null)
            throw new Exception("Room cannot be null");
        
        UserCard us = new UserCard(user, card); 
        us.setRoomOwner(isOwner);
        room.addUserCard(us);
    }
}
