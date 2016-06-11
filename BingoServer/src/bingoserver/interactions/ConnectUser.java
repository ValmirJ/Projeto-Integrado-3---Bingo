/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.Room;
import bingoserver.models.User;
import bingoserver.network.Client;
import bingoserver.repositories.RoomRepository;
import bingoserver.repositories.UserRepository;
import bingoserver.responses.AlreadyUsedRa;
import bingoserver.responses.AvailableRoomsResponse;
import bingoserver.responses.InvalidRa;
import bingoserver.responses.UserConnectedResponse;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author 15096134
 */
public class ConnectUser extends UserInteractor {

    public ConnectUser() {
        super();
    }

    @Override
    public void perform(JSONObject params) throws Exception {
        String ra = (String) params.get("ra");

        if (ra == null || ra.isEmpty()) {
            getResponseManager().respondToClient(new InvalidRa(), getCurrentClient());
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "User sent invalid RA");
            return;
        }

        UserRepository userRepo = getRepositoryManager().getUserRepository();
        RoomRepository roomRepo = getRepositoryManager().getRoomRepository();

        User user = userRepo.findUserWithRa(ra);

        if (user != null) {
            // Já existe um usuário com o RA acima
            //
            // Esse é um dos poucos, se não único lugar em que deve ser usado
            // getResponseManager().respondToClient()
            // Isso ocorre porque o client ainda não esta associado a um user.
            getResponseManager().respondToClient(new AlreadyUsedRa(), getCurrentClient());
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "User sent already used RA");
            return;
        }

        // Não existe usuário com o RA:
        user = userRepo.createUserWithRa(ra);

        //
        // Isso associa o client dessa requisição com o usuário criado para ele,
        // permitindo que usemos mgr.getUser() nas próximas vezes que esse client
        // fizer requisições.
        setSessionUser(user);

        // Avisa o client que ele foi conectado
        getResponseManager().respondToUser(new UserConnectedResponse(), getSessionUser());

        // Envia para o client as salas disponíveis,
        // bem como os usuários que estão nelas.
        HashMap<Room, List<User>> rooms = roomRepo.currentOpenRoomsWithUsers();
        getResponseManager().respondToUsers(new AvailableRoomsResponse(rooms), userRepo.usersWithoutRoom(roomRepo.getUsersInAnyRoom()));
    }
}
