/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class UserRepository {
    ArrayList<User> users;

    public List<User> usersWithoutRoom(List<User> usersWithRoom) {
        List<User> usersWithoutRoom = null;
        for(User u: users) {
            if(!usersWithRoom.contains(u))
                usersWithoutRoom.add(u);
        }
        return usersWithoutRoom;
    }
}
