/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author X230
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ServerSocket serverSocket = new ServerSocket(8080);
        
        MonitorServer monitor = new MonitorServer();
        
        while(true){
            Socket socket = serverSocket.accept();
            SendToClient sendToClient = new SendToClient(socket);
            monitor.AddClient(sendToClient);
            ListenToClient listenToClient = new ListenToClient(socket, null);
            sendToClient.start();
            listenToClient.start();
        }
    }
}
