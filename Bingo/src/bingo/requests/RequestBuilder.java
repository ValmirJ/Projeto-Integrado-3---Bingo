/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.requests;

//import bingoserver.interactions.AssignUserToRoom;
import bingo.GameDelegate;
import bingo.interactions.AssignUserToRoom;
import bingo.interactions.BingoWinned;
import bingo.interactions.ConnectWithRa;
import bingo.interactions.CreateRoom;
import bingo.interactions.DisplayGenericError;
import bingo.interactions.EverybodyHasGone;
import bingo.interactions.FinalInterval;
import bingo.interactions.Interactor;
import bingo.interactions.IntervalBegin;
import bingo.interactions.IntervalChange;
import bingo.interactions.InvalidBingo;
import bingo.interactions.ListRooms;
import bingo.interactions.UpdateListUsersInRoom;
import bingo.interactions.NewNumber;
import bingo.interactions.NotEnoughtPlayers;
import bingo.interactions.RemovedFromRoom;
import bingo.interactions.StartGame;
import bingo.interactions.YouLose;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author guilherme
 */
public class RequestBuilder {

    private HashMap<Object, Class<? extends Interactor>> mappings;

    public RequestBuilder() {
        mappings = new HashMap<>();
        mappings.put("logado-no-jogo", ConnectWithRa.class);
        mappings.put("ra-em-uso", ConnectWithRa.class);
        mappings.put("ra-invalido", ConnectWithRa.class);
        mappings.put("salas-disponiveis", ListRooms.class);
        mappings.put("sala-criada", CreateRoom.class);
        mappings.put("sala-sem-jogadores", NotEnoughtPlayers.class);
        mappings.put("aceito-na-sala", AssignUserToRoom.class);
        mappings.put("removido-da-sala", RemovedFromRoom.class);
        mappings.put("sala-cheia", AssignUserToRoom.class);
        mappings.put("usuarios-conectados-na-sua-sala", UpdateListUsersInRoom.class);
        mappings.put("erro-ao-processar-comando", DisplayGenericError.class);
        mappings.put("inicio-de-jogo", StartGame.class);
        mappings.put("interval-begin", IntervalBegin.class);
        mappings.put("interval-decrase", IntervalChange.class);
        mappings.put("final-interval-begin", FinalInterval.class);
        mappings.put("new-number", NewNumber.class);
        
        mappings.put("you-lose", YouLose.class);
        mappings.put("everybody-has-gone", EverybodyHasGone.class);
        mappings.put("bingo-winned", BingoWinned.class);
        mappings.put("bingo-invalido", InvalidBingo.class);
        
    }

    public String toString() {
        return "RequestBuilder";
    }

    public InteractionRequest buildRequestForMessage(String message) {
        // Os valores aceitaveis para message sao todos aqueles que os clientes
        // podem enviar para nosso servidor.
        //
        // 1. Verificar a sintaxe da mensagem.
        //
        // 2. Identificar a classe do Interactor correspondente.
        //
        // Retornar um InteractionRequest se houve sucesso nos passos acima.
        // OU
        // return new InvalidRequest(message);
        // Se houve alguma falha.

        try {
            JSONObject object = (JSONObject) JSONValue.parse(message);

            Object type = object.get("type");
            if (mappings.containsKey(type)) {
                Class<? extends Interactor> interactor = mappings.get(type);

                return new InteractionRequest(message, interactor, object);
            }
        } catch (Exception ex) {
            Logger.getLogger(GameDelegate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; //Se o servidor enviar algo muitooo errado!
    }
}
