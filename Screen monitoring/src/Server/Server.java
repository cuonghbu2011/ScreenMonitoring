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
import java.net.Socket;
/**
 *
 * @author X230
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException
    {        
        ServerSocket serverSocket = new ServerSocket(8080);
        
        while(true){
            Socket socket = serverSocket.accept();
            SendToClient sendToClient = new SendToClient(socket, "TEST_NE_PA");
            ListenToClient listenToClient = new ListenToClient(socket, null);
            sendToClient.start();
            listenToClient.start();
        }
    }
}
