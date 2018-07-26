/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

/**
 *
 * @author Administrator
 */
public class MonitorServer {
    public Hashtable lsClient;
    public BigForm _giaodien;
    ServerSocket serverSocket;
    public MonitorServer()
    {
        lsClient = new Hashtable<String, Socket>();
        _giaodien = new BigForm(lsClient);
        _giaodien.setVisible(true);
        try 
        {
            serverSocket = new ServerSocket (8080);
            while(true)
            {
              Socket soc = serverSocket.accept();
              lsClient.put(soc.getInetAddress().getHostName(), soc);
              _giaodien.capnhatClient();
              ListenToClient nghe = new ListenToClient(serverSocket, _giaodien);
              String tenThuMuc = soc.getInetAddress().getHostName();
              _giaodien.addClient(tenThuMuc+ "\\screen222.jpg");
              
              Thread t = new Thread((Runnable) nghe);
              t.start();
            }
        } catch(Exception e){e.printStackTrace();}
        
    }
}
