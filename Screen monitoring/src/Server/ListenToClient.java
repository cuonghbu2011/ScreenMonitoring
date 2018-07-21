
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

/**
 *
 * @author Administrator
 */
public class ListenToClient {
    public Socket incoming;
    public BigForm giaodien;
    public ListenToClient(Socket inSoc, BigForm _giaodien)
    {
        incoming = inSoc;
        giaodien = _giaodien;
        System.out.println("Bat dau lang nghe tu client: " + inSoc.toString());
    }
    
    public void run() throws IOException
    {
        while(true)
        {
            System.out.println("Dang nhan file tu " + incoming.getInetAddress().getHostAddress());
            InputStreamReader bis = new InputStreamReader(incoming.getInputStream());
            BufferedReader in = new BufferedReader(bis);
            String str = in.readLine();
            System.out.println(str);
            StringTokenizer strToken = new StringTokenizer(str);
            strToken.nextToken();
            
            long size = Long.parseLong(strToken.nextToken());
            System.out.println("File size " + size);
            DataInputStream dis = new DataInputStream(incoming.getInputStream());
            String tenThumuc = incoming.getInetAddress().getHostName();
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
            giaodien.paint (giaodien.getGraphics());
        }
    }
}
