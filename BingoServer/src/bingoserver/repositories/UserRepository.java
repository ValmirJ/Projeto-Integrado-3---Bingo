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

    private final List<User> holdUsers = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private int idCount = 0;

    public List<User> usersWithoutRoom(List<User> usersWithRoom) {
        List<User> usersWithoutRoom = new ArrayList<>();

        for (User u : users) {
            if (!usersWithRoom.contains(u)) {
                usersWithoutRoom.add(u);
            }
        }
        return usersWithoutRoom;
    }

    public User findUserWithRa(String ra) {
        for (User u : users) {
            if (u.getRa().equals(ra)) {
                return u;
            }
        }
        
        for (User u : holdUsers) {
            if (u.getRa().equals(ra)) {
                return u;
            }
        }

        return null;
    }

    public User createUserWithRa(String ra) {
        User u = new User(idCount++, ra);
        users.add(u);
        return u;
    }
    
    public void removeUser(User user) {
        users.remove(user);
        holdUsers.remove(user);
    }
    
    public void holdUser(User user) {
        if (users.remove(user)) {
            holdUsers.add(user);
        }
    }
    
    public void unholdUser(User user) {
        if (holdUsers.remove(user)) {
            users.add(user);
        }
    }
}
