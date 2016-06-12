/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class RepositoryManager {

    private final RoomRepository roomRepo = new RoomRepository();
    private final UserRepository userRepo = new UserRepository();
    private final UserCardRepository userCardRepo = new UserCardRepository();
    private final CardRepository cardRepository;
    private final Connection dbConnection;

    public RepositoryManager() throws Exception {
        dbConnection = DbConnectionBuilder.getNewConnection();
        cardRepository = new CardRepository(dbConnection);
    }

    public RoomRepository getRoomRepository() {
        return roomRepo;
    }

    public CardRepository getCardRepository() {
        return cardRepository;
    }

    public UserCardRepository getUserCardRepository() {
        return userCardRepo;
    }

    public UserRepository getUserRepository() {
        return userRepo;
    }

    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
