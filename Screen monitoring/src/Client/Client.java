/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Client 
{
    
    public static void main(String[] args) throws IOException
    {        
        boolean testConn = testConnection("127.0.0.1", 8080);
     
        Socket s = new Socket("127.0.0.1", 8080);
        SocketAddress sa = new InetSocketAddress("localhost", 8080);
        s.connect(sa);
        
        ListenToServer serverListener = new ListenToServer(s);
        serverListener.run();
    }
    
    private static boolean testConnection(String hostname, int port) {
        boolean sent = false;

        try {
            Socket socket = new Socket(hostname, port);

            OutputStream out = socket.getOutputStream();

            out.write(new byte[] {1});

            socket.close();

            sent = true;
        } catch (UnknownHostException e) {
            return sent;
        } catch (IOException e) {
            return sent;
        }

        return sent;
    }
}

