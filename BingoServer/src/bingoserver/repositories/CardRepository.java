/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;

/**
 *
 * @author guilherme
 */
public class CardRepository {

    public int countCards() {
        return 10;
    }

    public BingoCard getRandomCard() {

        return null;
    }

    public BingoCard getRandomCardAvailableForRoom(Room room) {
        return null;
    }
}
