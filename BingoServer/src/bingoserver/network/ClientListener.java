/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.network;

import bingoserver.messages.Message;

/**
 *
 * @author 15096134
 */
public interface ClientListener {
    public void onClientDisconnected(Client c);
    public void onClientMessage(Client c, Message m);
}
