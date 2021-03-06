
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Models.IProcess;
import Models.IRequest;
import Models.RequestImageFile;
import Models.ProcessBase;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ListenToClient extends Thread{
    public Socket _socket;
    public BigForm _giaodien;
    
    public ListenToClient(Socket socket, BigForm giaodien)
    {
        _socket = socket;
        _giaodien = giaodien;
        System.out.println("Bat dau lang nghe tu client: " + socket.toString());
    }
    
    
    
    public void run()
    {
        try {
            receiveFile1();
            
            //SendToClient sender = new SendToClient(_socket, "TEST_NE_BA");
            //sender.start();
        } catch (IOException ex) {
            Logger.getLogger(ListenToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveFile() throws IOException{
        try{
            System.out.println("Dang nhan file tu " + _socket.getInetAddress().getHostAddress());
            InputStreamReader bis = new InputStreamReader(_socket.getInputStream());
            BufferedReader in = new BufferedReader(bis);
            String str = in.readLine();
            if (str == ""){
                return;
            }
            System.out.println(str);
            StringTokenizer strToken = new StringTokenizer(str);
            //strToken.nextToken();
            //long size = Long.parseLong(token);
            //System.out.println("File size " + size);
            DataInputStream dis = new DataInputStream(_socket.getInputStream());
            String tenThumuc = _socket.getInetAddress().getHostName();
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
            byte[] data = new byte[16 * 1024];
            while(count != -1)
            {
                i = dis.read(data);
                fout.write(data,0,i);
                count+=i;
            }
            fout.flush();
            _giaodien.paint(_giaodien.getGraphics());
            fout.close();
            in.close();
            bis.close();
            dis.close();
            //_socket.close();
        }catch(Exception ex){
            //_socket.close();
            //_serverSocket.close();
            Logger.getLogger(ListenToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveFile1() throws IOException{
        try{            
            ObjectInputStream oIS = new ObjectInputStream(_socket.getInputStream());
            
            while(true){
                RequestImageFile sF = (RequestImageFile)oIS.readObject();
                
                if (sF == null){
                    break;
                }
                
                System.out.println("Dang nhan file tu " + _socket.getInetAddress().getHostAddress());

                //String tenThumuc = _socket.getInetAddress().getHostName();
                String tenThumuc = sF.Folder;
                if (tenThumuc == null || tenThumuc == ""){
                    tenThumuc = _socket.getInetAddress().getHostName();
                }
                File thumuc = new File(tenThumuc);
                if (!thumuc.exists())
                {
                    thumuc.mkdir();
                }
                File f = new File(tenThumuc + "\\" + sF.Name + sF.Type);
                if(!f.exists())
                {
                    f.createNewFile();
                }

                FileOutputStream fout = new FileOutputStream(f,false);
                fout.write(sF.Content);

                fout.flush();
                System.out.println("Nhan xong");
            }
            
            //fout.close();
            //oIS.close();
            //_socket.close();
            System.out.println("Het file");
        }catch(Exception ex){
            //_socket.close();
            //_serverSocket.close();
            Logger.getLogger(ListenToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receiveFile2() throws IOException{
        try{
            System.out.println("Dang nhan file tu " + _socket.getInetAddress().getHostAddress());
            
            ObjectInputStream oIS = new ObjectInputStream(_socket.getInputStream());
            
            while(true){
                IRequest request = (IRequest)oIS.readObject();
                
                if (request == null){
                    break;
                }

                IProcess process = new ProcessBase(request, _socket);
                process.Process();
            }
            
            //fout.close();
            //oIS.close();
            //_socket.close();
            System.out.println("Het file");
        }catch(Exception ex){
            //_socket.close();
            //_serverSocket.close();
            Logger.getLogger(ListenToClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
