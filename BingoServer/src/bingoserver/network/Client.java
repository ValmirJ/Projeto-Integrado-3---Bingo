/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import bingoserver.responses.Response;
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
public class Client implements Cloneable{

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

    public String read() {
        try {
            String message = input.readLine();

            if (message != null) {
                return message;
            } else {
                Logger.getLogger(Client.class.getName()).log(Level.WARNING, null, "Received null Client message");
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            listener.onClientDisconnected(this);
        }

        return null;
    }
    
    @Override
    public int hashCode() {
        int r = super.hashCode();
        r = r * 7 + this.socket.hashCode();
        r = r * 7 + this.listener.hashCode();
        r = r * 7 + this.input.hashCode();
        r = r * 7 + this.output.hashCode();
       
        return r;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        
        if(!(obj instanceof Client))
            return false;
        
        Client other = (Client) obj;
        if(!(this.socket.equals(other.socket)))
            return false;
        if(!(this.listener.equals(other.listener)))
            return false;
        
        return true;
    }
    @Override
    public String toString() {
        String str = "Socket: " + this.socket.toString();
        
        return str;
    }
    
    public Client(Client other) throws Exception{
        if(other == null)
            throw new Exception("Objeto não pode ser nulo");
        
        this.listener = other.listener;
        this.socket = other.socket;
        this.output = other.output;
        this.input = other.input;
    }
    
    @Override 
    public Object clone() {
        Client newClient = null;
        try {
            newClient = new Client(this);
        }
        catch(Exception e) {}
        
        return newClient;
    }
}
