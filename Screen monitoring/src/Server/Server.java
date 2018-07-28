/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.ProcessImage;
import Models.ProcessBase;
import java.io.IOException;
import java.net.ServerSocket;
/**
 *
 * @author X230
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException
    {        
        ServerSocket serverSocket = new ServerSocket(8080);
        Thread tIn = new Thread(new ListenToClient(serverSocket, null));
        tIn.run();
        //ListenToClient listenToClient = new ListenToClient(serverSocket, null);
        //listenToClient.run();
    }
}
