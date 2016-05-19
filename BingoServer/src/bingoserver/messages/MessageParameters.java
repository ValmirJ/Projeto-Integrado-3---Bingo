/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver.messages;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 15096134
 */
public class MessageParameters {
    private final List<List<String>> params;

    public MessageParameters(Message m) {
        this.params = new ArrayList<>();
    };
}
