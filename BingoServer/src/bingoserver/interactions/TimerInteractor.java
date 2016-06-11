/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.BingoServer;
import bingoserver.models.Room;
import bingoserver.repositories.RoomRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class TimerInteractor extends Interactor {

    public TimerInteractor() {
        super();
    }

    public void perform() {
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();
        
        List<Room> preStartedrooms = roomRepo.getPrestartedRooms();
    }
    
    private void processPrestarted(List<Room> rooms) {
        
    }
}
