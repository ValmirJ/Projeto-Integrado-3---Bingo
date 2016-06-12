/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class CardRepository {

    private final Connection dbConnection;

    public CardRepository(Connection dbConnection) throws Exception {
        if (dbConnection == null) {
            throw new Exception("Connection is null");
        }
        this.dbConnection = dbConnection;
    }

    public int countCards() throws SQLException {
        int countResult = 0;
        String sql = "SELECT COUNT(cartelas.nr) AS count "
                + "FROM cartelas";

        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.closeOnCompletion();
        ResultSet result = stmt.executeQuery();
        result.next();

        countResult = result.getInt("count");
        result.close();
        return countResult;
    }

    public BingoCard getRandomCard() throws SQLException {
        int idCard;

        String sql = "SELECT(cartelas.nr) "
                + "FROM cartelas "
                + "ORDER BY RAND() "
                + "LIMIT 1;";

        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.closeOnCompletion();
        ResultSet result = stmt.executeQuery();
        result.first();
        idCard = result.getInt("nr");
        result.close();

        return getBingoCard(idCard);
    }

    public BingoCard getRandomCardAvailableForRoom(Room room) throws SQLException, Exception {
        if (room == null) {
            throw new Exception("Room cannot be null");
        }

        List<Integer> inUse = room.getCardsIds();

        if (inUse.isEmpty()) {
            return getRandomCard();
        }

        DbUtils.MultipleBinder binder = DbUtils.buildBinder(inUse);

        String sql = "SELECT(cartelas.nr) "
                + "FROM cartelas "
                + "WHERE nr NOT IN " + binder.questionMarkString() + " "
                + "ORDER BY RAND() "
                + "LIMIT 1;";

        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.closeOnCompletion();
        binder.setValuesAsInt(stmt);
        ResultSet result = stmt.executeQuery();
        result.first();
        int idCard = result.getInt("nr");
        result.close();

        return getBingoCard(idCard);
    }

    private BingoCard getBingoCard(int nrCartela) throws SQLException {
        String sql = "SELECT * "
                + "FROM combinacoes "
                + "WHERE nrCartela = ?";

        PreparedStatement stmt = dbConnection.prepareStatement(sql);
        stmt.setInt(1, nrCartela);
        stmt.closeOnCompletion();
        ResultSet result = stmt.executeQuery();

        int cardNumbers[][] = new int[5][5];

        while (result.next()) {
            int lin = result.getInt("linha");
            int col = result.getInt("coluna");
            int val = result.getInt("valor");

            if (lin < 5 && col < 5) {
                cardNumbers[lin][col] = val;
            }
        }

        result.close();

        BingoCard card = null;

        try {
            card = new BingoCard(nrCartela, cardNumbers);
        } catch (Exception ex) {
            Logger.getLogger(CardRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return card;
    }
}
