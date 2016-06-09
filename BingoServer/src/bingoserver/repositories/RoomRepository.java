/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.models.UserCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class RoomRepository {

    ArrayList<Room> rooms;

    public Room createRoom(UserCard us) throws Exception {
        if(us == null)
            throw new Exception("UserCard cannot be null");

        Room newRoom = new Room(rooms.size(), us);
        rooms.add(newRoom);
        return newRoom;
    }

    public Room findRoomByUser(User user) throws Exception {
        if(user == null)
            throw new Exception("User cannot be null");
        
        for(Room r: rooms) {
            List<User> usersInRoom = r.getUsers();
            if(usersInRoom.contains(user)) {
                return r;
            }
        }
        return null;
    }
    
    public Room roomOwnedBy(User user) throws Exception {
        Room room = findRoomByUser(user);
        
        if (room.getRoomOwner() == user) {
            return room;
        }
        
        return null;
    }

    public void startRoom(Room room) {
        for (Room r : rooms) {
            if (r.equals(room)) {
                r.setState(Room.RoomState.prestarted);
            }
        }
    }
    
    public Room findRoomById(int roomId) {
        for(Room r: rooms) {
            if(r.getId() == roomId)
                return r;
        }
        return null;
    }

    public Room findRoomById(String roomId) throws Exception{
        if(roomId == null)
            throw new Exception("String RoomId cannot be null");
        
        int ri = Integer.parseInt(roomId);
        
        return findRoomById(ri);
    }

    public List<User> usersInRoom(Room room) throws Exception {
        if(room == null)
            throw new Exception("Room cannot be null");
        
        return room.getUsers();
    }

    public HashMap<Room, List<User>> currentOpenRoomsWithUsers() {
        HashMap<Room, List<User>> openRooms = null;
        for(Room r : rooms) {
            openRooms.put(r, r.getUsers());
        }
        return openRooms;
    }

    public void removeRoom(Room room) throws Exception {
        if(room == null)
            throw new Exception("Room cannot be null");
        
        rooms.remove(room);
    }
    
    public List<User> getUsersInAnyRoom() {
        List<User> usersWithRoom = null;
        for(Room r : rooms) {
            usersWithRoom.addAll(r.getUsers());
        }
        return usersWithRoom;
    }
}
