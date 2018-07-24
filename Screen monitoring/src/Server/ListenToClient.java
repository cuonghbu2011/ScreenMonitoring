
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ListenToClient extends Thread{
    public Socket _incoming;
    public BigForm _giaodien;
    
    public ListenToClient(Socket inSoc, BigForm giaodien)
    {
        _incoming = inSoc;
        _giaodien = giaodien;
        System.out.println("Bat dau lang nghe tu client: " + inSoc.toString());
    }
    
    public void run()
    {
        try {
            while(true)
            {
                System.out.println("Dang nhan file tu " + _incoming.getInetAddress().getHostAddress());
                InputStreamReader bis;  
                bis = new InputStreamReader(_incoming.getInputStream());
                BufferedReader in = new BufferedReader(bis);
                String str = in.readLine();
                System.out.println(str);
                StringTokenizer strToken = new StringTokenizer(str);
                strToken.nextToken();
                long size = Long.parseLong(strToken.nextToken());
                System.out.println("File size " + size);
                DataInputStream dis = new DataInputStream(_incoming.getInputStream());
                String tenThumuc = _incoming.getInetAddress().getHostName();
                File thumuc = new File(tenThumuc);
                if (!thumuc.exists())
                {
                    thumuc.mkdir();
                }
                File f = new File(tenThumuc + "\\screen222.jpg");
                if(!f.exists())
                {
                    f.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(f,false);
                long count= 0;
                int i;
                byte[] data = new byte[1024];
                while(count<size)
                {
                    i = dis.read(data);
                    fout.write(data,0,i);
                    count+=i;
                }
                fout.flush();
                fout.close();
                _giaodien.paint (_giaodien.getGraphics());
            }
        } catch (IOException ex) {
            Logger.getLogger(ListenToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
