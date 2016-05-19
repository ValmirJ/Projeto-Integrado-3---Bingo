/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 15096134
 */
public class Client {
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    
    private ClientListener listener;

    public ClientListener getListener() {
        return listener;
    }

    public void setListener(ClientListener listener) {
        this.listener = listener;
    }
    
    Client(Socket s) throws IOException {
        socket = s;
        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }
    
    public void send(Response resp) {
        try {
            output.write(resp.responseData());
            output.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            listener.onClientDisconnected(this);
        }
    }
    
    public Message read() {
        try {
            String message = input.readLine();
            return new Message(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            listener.onClientDisconnected(this);
        }
        
        return null;
    }
}
