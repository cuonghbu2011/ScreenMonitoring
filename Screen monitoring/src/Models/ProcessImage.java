/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Server.ListenToClient;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author X230
 */
public class ProcessImage extends ProcessBase{

    public ProcessImage(IRequest request, Socket socket) {
        super(request, socket);
    }
    
    @Override
    public void Process(){
        try{
            String tenThumuc = _socket.getInetAddress().getHostName();
            File thumuc = new File(tenThumuc);
            if (!thumuc.exists())
            {
                thumuc.mkdir();
            }
            
            RequestImageFile sF = (RequestImageFile)_request;
            
            File f = new File(tenThumuc + "\\" + sF.Name + sF.Type);
            if(!f.exists())
            {
                f.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(f,false);
            fout.write(sF.Content);

            fout.flush();
            
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
