/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author X230
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        
        Thread tIn = new Thread(new ListenToClient(socket, null));
        tIn.run();
    }
}
