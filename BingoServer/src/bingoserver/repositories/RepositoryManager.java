/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

/**
 *
 * @author 15096134
 */
public class RepositoryManager {

    private RoomRepository roomRepo = new RoomRepository();

    public RepositoryManager() {

    }

    public RoomRepository getRoomRepository() {
        return roomRepo;
    }

    public CardRepository getCardRepository() {
        return null;
    }

    public UserCardRepository getUserCardRepository() {
        return null;
    }

    public UserRepository getUserRepository() {
        return null;
    }
}
