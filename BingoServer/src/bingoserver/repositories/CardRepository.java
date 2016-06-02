/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.BingoCard;
import bingoserver.models.Room;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
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
        if(dbConnection == null)
            throw new Exception("Connection is null");
        this.dbConnection = dbConnection;
    }
    
    public int countCards() throws SQLException {
        int countResult = 0;
        String sql = "SELECT COUNT(cartelas.nr)"
                   + "FROM cartelas";
        
        PreparedStatement stmt = dbConnection.clientPrepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.closeOnCompletion();
        ResultSet result = stmt.executeQuery();
        result.next();
        
        countResult = result.getInt("count");
        result.close();
        return countResult;
    }

    public BingoCard getRandomCard() {
        //Quando vai usar esse método??
        return null;
    }

    public BingoCard getRandomCardAvailableForRoom(Room room) throws SQLException {
        
        List<BingoCard> cardsInUse = room.getCards();
        int idCard;
        int[][] card = new int[5][5];
        BingoCard resultBingoCard = null;
        
        String sql = "SELECT(cartelas.nr) "
                    + "FROM cartelas "
                    + "WHERE nr NOT LIKE " + cardsInUse.get(0);
        
        for(int i = 1; i < cardsInUse.size(); i++) {
            sql += "AND nr NOT LIKE " + cardsInUse.get(i);
        }
        sql += "ORDER BY RAND()"
              + "LIMIT 1;" ;
                
        PreparedStatement stmt = dbConnection.clientPrepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.closeOnCompletion();
        ResultSet result = stmt.executeQuery();
        result.first();
        idCard = result.getInt("nr");
        result.close();
        String sql2 = "SELECT * "
                + "FROM combinacoes"
                + "WHERE idCartela = ?";
        
        stmt = dbConnection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, idCard);
        stmt.closeOnCompletion();
        result = stmt.executeQuery();
        
        while(result.next()) {
            card[result.getInt("linha")][result.getInt("coluna")] = result.getInt("coluna");
        }
        result.close();
        try {
            resultBingoCard = new BingoCard(idCard, card);
        } catch (Exception ex) {
            Logger.getLogger(CardRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultBingoCard;
    }
}
