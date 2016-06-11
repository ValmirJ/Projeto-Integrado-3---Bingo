/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.BingoServer;
import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.RoomRepository;
import bingoserver.responses.IntervalBeginResponse;
import bingoserver.responses.IntervalDecraseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class TimerInteractor extends Interactor {
    private static final int INTERVAL_TIME = 3;
    private static final int SORT_TIME = 10;
    private Random rand;
    
    public TimerInteractor() {
        super();
        rand = new Random();
    }

    public void perform() {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        
        List<Room> preStartedrooms = roomRepo.getPrestartedRooms();
        List<Room> intervalRooms = roomRepo.getIntervalRooms();
        
        setToIntervalBegin(roomRepo, preStartedrooms);
        decraseInterval(roomRepo, intervalRooms);
    }
    
    private void setToIntervalBegin(RoomRepository roomRepo, List<Room> rooms) {
        for (Room room : rooms) {
            roomRepo.updateRoomState(room, Room.RoomState.interval, INTERVAL_TIME);
        
            try {
                List<User> users = roomRepo.usersInRoom(room);
                getResponseManager().respondToUsers(new IntervalBeginResponse(room.getTime()), users);
            } catch (Exception ex) {
                Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void decraseInterval(RoomRepository roomRepo, List<Room> rooms) {
        List<Room> roomsToRandom = new ArrayList<Room>();
        
        for (Room room : rooms) {
            int roomTime = room.getTime();
            
            if (roomTime > 0) {
                roomRepo.updateRoomState(room, Room.RoomState.interval, roomTime - 1);
                
                try {
                    List<User> users = roomRepo.usersInRoom(room);
                    getResponseManager().respondToUsers(new IntervalDecraseResponse(room.getTime()), users);
                } catch (Exception ex) {
                    Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                roomsToRandom.add(room);
            }
        }
        
        randomNextNumber(roomRepo, roomsToRandom);
    }
    
    private void randomNextNumber(RoomRepository roomRepo, List<Room> rooms) {
        for (Room room : rooms) {
            List<Integer>  blackList = room.getSortedNumbers();
            List<Integer> possibles = new ArrayList<>(75);
            
            for (int i=1; i<=75; i++)
                if (blackList.indexOf(i) < 0)
                    possibles.add(i);
            
            // TODO: ensure one number always available
            int random = rand.nextInt(possibles.size());
            int sorted = possibles.get(random);
            
            roomRepo.addRandomToRoom(room, sorted);
            
            // TODO: Send response to users
        }
    }
}
