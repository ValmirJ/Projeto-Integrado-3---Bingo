/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.repositories.RoomRepository;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.FinalIntervalResponse;
import bingoserver.responses.IntervalBeginResponse;
import bingoserver.responses.IntervalDecraseResponse;
import bingoserver.responses.SortedNumberResponse;
import bingoserver.responses.YouLoseResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class TimerInteractor extends Interactor {

    private final Random rand;

    public TimerInteractor() {
        super();
        rand = new Random();
    }

    public void perform() {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();

        List<Room> preStartedrooms = roomRepo.getPrestartedRooms();
        List<Room> intervalRooms = roomRepo.getIntervalRooms();
        List<Room> sortIntervalRooms = roomRepo.getSortIntervalRooms();
        List<Room> finalIntervalRooms = roomRepo.getFinalIntervalRooms();

        setToIntervalBegin(roomRepo, preStartedrooms);
        decraseInterval(roomRepo, intervalRooms);
        decraseSortInterval(roomRepo, sortIntervalRooms);
        decraseFinalInterval(roomRepo, finalIntervalRooms);
    }

    private void setToIntervalBegin(RoomRepository roomRepo, List<Room> rooms) {
        for (Room room : rooms) {
            roomRepo.updateRoomState(room, Room.RoomState.interval, Settings.INTERVAL_TIME);

            try {
                List<User> users = roomRepo.usersInRoom(room);
                getResponseManager().respondToUsers(new IntervalBeginResponse(room.getTime()), users);
            } catch (Exception ex) {
                Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void decraseInterval(RoomRepository roomRepo, List<Room> rooms) {
        for (Room room : rooms) {
            int roomTime = room.getTime();

            if (roomTime > 0) {
                roomRepo.updateRoomState(room, room.getState(), roomTime - 1);

                try {
                    List<User> users = roomRepo.usersInRoom(room);
                    getResponseManager().respondToUsers(new IntervalDecraseResponse(room.getTime()), users);
                } catch (Exception ex) {
                    Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                randomNextNumber(roomRepo, room);
            }
        }
    }

    private void randomNextNumber(RoomRepository roomRepo, Room room) {
        List<Integer> blackList = room.getSortedNumbers();
        List<Integer> possibles = new ArrayList<>(Settings.LAST_NUMBER);

        for (int i = 1; i <= Settings.LAST_NUMBER; i++) {
            if (blackList.indexOf(i) < 0) {
                possibles.add(i);
            }
        }

        int random = rand.nextInt(possibles.size());
        int sorted = possibles.get(random);

        roomRepo.addRandomToRoom(room, sorted);
        roomRepo.updateRoomState(room, Room.RoomState.sorting, Settings.SORT_TIME);

        try {
            List<User> users = roomRepo.usersInRoom(room);
            getResponseManager().respondToUsers(new SortedNumberResponse(room.getTime(), sorted), users);
        } catch (Exception ex) {
            Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void decraseSortInterval(RoomRepository roomRepo, List<Room> rooms) {
        List<Room> toInterval = new ArrayList<>();

        for (Room room : rooms) {
            int roomTime = room.getTime();

            if (roomTime > 0) {
                roomRepo.updateRoomState(room, room.getState(), roomTime - 1);

                try {
                    List<User> users = roomRepo.usersInRoom(room);
                    getResponseManager().respondToUsers(new IntervalDecraseResponse(room.getTime()), users);
                } catch (Exception ex) {
                    Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (room.getSortedNumbers().size() >= Settings.LAST_NUMBER) {
                setToFinalInterval(roomRepo, room);
            } else {
                toInterval.add(room);
            }
        }

        setToIntervalBegin(roomRepo, toInterval);
    }

    private void setToFinalInterval(RoomRepository roomRepo, Room room) {
        roomRepo.updateRoomState(room, Room.RoomState.finalInterval, Settings.FINAL_INTERVAL);

        try {
            List<User> users = roomRepo.usersInRoom(room);
            getResponseManager().respondToUsers(new FinalIntervalResponse(room.getTime()), users);
        } catch (Exception ex) {
            Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void decraseFinalInterval(RoomRepository roomRepo, List<Room> rooms) {
        for (Room room : rooms) {
            int roomTime = room.getTime();

            if (roomTime > 0) {
                roomRepo.updateRoomState(room, room.getState(), roomTime - 1);

                try {
                    List<User> users = roomRepo.usersInRoom(room);
                    getResponseManager().respondToUsers(new IntervalDecraseResponse(room.getTime()), users);
                } catch (Exception ex) {
                    Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    List<User> users = roomRepo.usersInRoom(room);
                    roomRepo.removeRoom(room);
                    sendLoseResponse(users);
                } catch (Exception ex) {
                    Logger.getLogger(TimerInteractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void sendLoseResponse(List<User> users) {
        for (User user : users)
            getRepositoryManager().getUserRepository().holdUser(user);
        
        getResponseManager().respondToUsers(new YouLoseResponse(), users);
    }
}
