/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ListenToServer extends Thread{
    private Socket _socket;
    public ListenToServer(Socket s)
    {
        _socket = s; 
    }
    public void run()
    {
        try
        {   
            while(true)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                String str = in.readLine();
                
                if (str.isEmpty()){
                    break;
                }
                
                System.out.println(str);
                /*
                if(str.toUpperCase().startsWith("SHUTDOWN"))
                {
                    Runtime.getRuntime().exec("Shutdown.exe -s -t 10");
                }
                if(str.toUpperCase().startsWith("RESTART"))
                {
                    Runtime.getRuntime().exec("Shutdown.exe -r");
                }
                if(str.toUpperCase().startsWith("LOGOFF"))
                {
                    Runtime.getRuntime().exec("Shutdown.exe -l");
                }
                if(str.toUpperCase().startsWith("CHAT"))
                {
                    StringTokenizer strToken = new StringTokenizer (str);
                    String strChat = str.substring(4,str.length());
                    JOptionPane.showMessageDialog(null, strChat);
                }*/
                //in.close();
                //_socket.close();
            }
        } catch (Exception e){
            System.err.println("Socket closed");
        }
    }
}
