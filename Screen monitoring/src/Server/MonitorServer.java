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
    public Hashtable dsClient;
    public BigForm giaodien;
    ServerSocket ss;
    public MonitorServer()
    {
        dsClient = new Hashtable<String, Socket>();
        giaodien = new BigForm(dsClient);
        giaodien.setVisible(true);
        try 
        {
            ss = new ServerSocket (1999);
            while(true)
            {
              Socket soc = ss.accept();
              dsClient.put(soc.getInetAddress().getHostName(), soc);
              giaodien.capnhatClient();
              ListenToClient nghe = new ListenToClient(soc, giaodien);
              String tenThuMuc = soc.getInetAddress().getHostName();
              giaodien.addClient(tenThuMuc+ "\\screen222.jpg");
              
              Thread t = new Thread((Runnable) nghe);
              t.start();
            }
        } catch(Exception e){e.printStackTrace();}
        
    }
}
