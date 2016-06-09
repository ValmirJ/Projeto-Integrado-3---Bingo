/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.network;

import bingo.responses.Response;
import java.net.Socket;

/**
 *
 * @author 15174782
 */
public class ClientManager {
    private Client oneClient;
    
    
    public ClientManager(Socket socket, ClientListener cl) throws Exception {
        if(socket == null)
            throw new Exception("Socket cannot be null");
        if(cl == null)
            throw new Exception("ClientListener cannot be null");
        
        oneClient = new Client(socket, cl);
    }
    
    public void sendMessage(Response res) {
        oneClient.send(res.responseData());
    }  
}
