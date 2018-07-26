/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.Socket;
public class Client 
{
    
    public static void main(String[] args) throws IOException
    {                
        //Thread tIn = new Thread(new ListenToServer(s));
        //tIn.run();
        
        //Thread tOut = new Thread(new SendToServer(s));
        //tOut.run();
        Socket s = new Socket("127.0.0.1", 8080);
        SendToServer toServer = new SendToServer(s);
        toServer.run();
    }
}