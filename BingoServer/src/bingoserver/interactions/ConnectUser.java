/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.interactions;

import bingoserver.models.User;
import bingoserver.parameters.ParamGroup;
import bingoserver.parameters.ParamGroups;
import bingoserver.responses.RaAlreadyInUse;
import bingoserver.responses.UserConnectedResponse;
import bingoserver.session.UserClientSession;

/**
 *
 * @author 15096134
 */
public class ConnectUser extends UserInteractor {

    public ConnectUser() {
        super();
    }

    @Override
    public void perform(ParamGroups params) {
        String ra = params.getParamGroup(0).getParam(0);
        User user = null;

        // TODO:
        // user = getRepositoryManager().getUserRepository().findUserWithRa(ra);
        // Como ainda não temos o repositorio de usuarios:
        if (user != null) {
            // Já existe um usuário com o RA acima
            ParamGroup group0 = new ParamGroup(ra);
            ParamGroups groups = new ParamGroups(group0);

            // Esse é um dos poucos, se não único lugar em que deve ser usado
            // getResponseManager().respondToClient()
            // Isso ocorre porque o client ainda não esta associado a um user.
            getResponseManager().respondToClient(new RaAlreadyInUse(groups), getUserClientSession().getClient());
            return;
        }

        // Não existe usuário com o RA:
        // user = getRepositoryManager().getUserRepository().createUserWithRa(ra);
        //
        // Isso associa o client dessa requisição com o usuário criado para ele,
        // permitindo que usemos mgr.getUser() nas próximas vezes que esse client
        // fizer requisições.
        setUserClientSession(new UserClientSession(getUserClientSession().getClient(), user));

        // Ja está feito:
        // Avisar o client que ele foi conectado
        getResponseManager().respondToClient(new UserConnectedResponse(), getUserClientSession().getClient());

        // TODO:
        // Enviar para o client as salas disponíveis,
        // bem como os usuários que estão nelas.
        //
        // Note que no exemplo abaixo ainda terá que ser feita a iteração, bem como
        // as classes AvaiableRoomsResponse e WhoIsInRoomResponse
        //
        // Room[] rooms = getRepositoryManager().getRooms();
        //
        // ParamGroup groupRooms = new ParamGroup(rooms[0].id, rooms[1].id, ....);
        // ParamGroups groups = new ParamGroups(group0);
        // getResponseManager().respondToClient(new AvaiableRoomsResponse(groups), getUserClientSession().getClient());
        //
        // ParamGroup roomId = new ParamGroup(rooms[0].id);
        // ParamGroup whoIsInRoom0 = new ParamGroup(rooms[0].users[0].id, rooms[0].users[1].id,....);
        // ParamGroups groups = new ParamGroups(roomId, whoIsInRoom0);
        // getResponseManager().respondToClient(new WhoIsInRoomResponse(groups), getUserClientSession().getClient());
    }
}
