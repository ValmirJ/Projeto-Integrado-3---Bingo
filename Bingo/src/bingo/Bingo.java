/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import bingo.network.Client;
import bingo.network.ClientListener;
import bingo.network.ClientManager;
import bingo.requests.RequestBuilder;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class Bingo implements ClientListener {

    private FormManager formController;
    private ClientManager clientManager;
    private final RequestBuilder requestBuilder = new RequestBuilder();

    public Bingo() {
        this.formController = new FormManager(clientManager);

    }

    public static void main(String... args) throws IOException, ClassNotFoundException {
        new Bingo().execute();

    }

    private void execute() {
        try {
            final Socket socket = new Socket("127.0.0.1", 10001);
            socket.setTcpNoDelay(true);
            this.clientManager = new ClientManager(socket, this);

        } catch (IOException ex) {
            Logger.getLogger(Bingo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(Bingo.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void onClientConnected(Client client) {
        this.formController.getTelaInicial().setVisible(true);
    }

    @Override
    public void onClientDisconnected(Client client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClientMessage(Client client, String message) {
        //InteractionRequest requestResponse = (InteractionRequest) this.requestBuilder.buildRequestForMessage(message);
        //if(requestResponse.valid() && !requestResponse.equals(null)) {
        //    Class<? extends Interactor> interactionClass = requestResponse.getInteractorClass();
        //}
    }
}
