/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author X230
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        ServerSocket s = new ServerSocket(8080);
        while (true) {
            try {
                // Wait for new clients and accept them
                Socket client = s.accept();
                // Let the user know - print
                System.out.println("New user connected - " + client.getLocalAddress().getHostAddress());
                // Start thread for our client
                
                BufferedReader readClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                while ((line = readClient.readLine()) != null) {
                    System.out.println("Client says - " + readClient.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
