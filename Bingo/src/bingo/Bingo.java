/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author guilherme
 */
public class Bingo {

    public static void main(String... args) throws IOException, ClassNotFoundException {
        System.out.println("Teste");
        Socket socket = new Socket("127.0.0.1", 10001);
        socket.setTcpNoDelay(true);

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        output.writeUTF("teste");
        output.flush();
        System.out.println(input.readUTF());

        output.close();
        input.close();
        socket.close();
    }
}
