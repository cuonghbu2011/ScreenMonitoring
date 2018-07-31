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
    
    public SendToClient(Socket socket){
        _socket = socket;
    }
    
    public SendToClient(Socket socket, String s) throws IOException{
        this(socket);
        _inputString = s;
        _out = new PrintWriter(_socket.getOutputStream());
    }
    
    public String GetClientIP(){
        return _socket.getInetAddress().getHostAddress();
    }
    
    public String GetClientName(){
        return _socket.getInetAddress().getHostName();
    }
    
    public PrintWriter _out = null;
    
    public PrintWriter GetPrintWriter(){
        return _out;
    }
    
    public void Send(String msg){
        _out.println(msg);
        _out.flush();
    }

    public void run() {
        try {            
            while(true){
                if (_inputString != null){
                    Send(_inputString);
                }
                Thread.sleep(10000);
            }
            
            //out.close();
            //_socket.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(SendToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
