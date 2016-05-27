/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.repositories;

import bingoserver.models.Room;
import bingoserver.models.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class RoomRepository {

    private final Connection dbConnection;

    public RoomRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Room createRoom() throws SQLException {
        PreparedStatement stmt = dbConnection
                .clientPrepareStatement("INSERT INTO rooms (started) VALUES (0);", Statement.RETURN_GENERATED_KEYS);

        stmt.closeOnCompletion();
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();

        int roomId = rs.getInt(0);

        rs.close();

        return new Room(roomId, false);
    }

    public Room findRoomByUser(User user) throws SQLException {
        return null;
    }

    public Room findRoomById(int roomId) {
        return null;
    }

    public Room findRoomById(String roomId) {
        return null;
    }

    public List<User> usersInRoom(Room room) {
        return null;
    }

    public HashMap<Room, List<User>> currentOpenRoomsWithUsers() {
        return null;
    }

    public void removeRoom(Room room) {

    }
}
