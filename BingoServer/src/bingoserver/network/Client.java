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
public class Client implements Cloneable {

    private final Socket socket;
    private final BufferedReader input;
    private final BufferedWriter output;
    private final ClientListener listener;

    Client(Socket socket, ClientListener listener) throws IOException {
        if (socket == null) {
            throw new NullPointerException("Socket cannot be null");
        }

        if (listener == null) {
            throw new NullPointerException("ClientListener cannot be null");
        }

        this.socket = socket;
        this.listener = listener;

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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

    private void read() {
        try {
            String message = input.readLine();

            if (message != null) {
                listener.onClientMessage(this, message);
            } else {
                Logger.getLogger(Client.class.getName()).log(Level.WARNING, null, "Received null Client message");
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            listener.onClientDisconnected(this);
        }
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
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }

        Client other = (Client) obj;
        if (!(this.socket.equals(other.socket))) {
            return false;
        }
        if (!(this.listener.equals(other.listener))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String str = "Socket: " + this.socket.toString();

        return str;
    }

    public Client(Client other) {
        if (other == null) {
            throw new NullPointerException("Client cannot be null");
        }

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
        } catch (Exception e) {
        }

        return newClient;
    }

    void start() {
        listener.onClientConnected(this);
    }
}
