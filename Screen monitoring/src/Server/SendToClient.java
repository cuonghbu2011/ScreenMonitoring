/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X230
 */
public class SendToClient extends Thread{
    
    private Socket _socket;
    private String _inputString;
    
    public SendToClient(Socket socket, String s){
        _socket = socket;
        _inputString = s;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(_socket.getOutputStream());
            
            while(true){
                out.println(_inputString);
                out.flush();
                Thread.sleep(10000);
            }
            
            //out.close();
            //_socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SendToClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SendToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
