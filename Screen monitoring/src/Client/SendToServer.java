/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class SendToServer {
    
    private Socket _socket;
    
    public SendToServer(Socket s){
        _socket = s;
    }
    
    public void chupAnhManHinh()
    {
        try
        {
            String outFileName = "screen.jpg";
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            ImageIO.write(image,"jpg", new File(outFileName));
            System.out.println("Da luu vao file" + outFileName + ".");
        } catch(Exception e){e.printStackTrace();}
    }
    public void guiAnhVeServer()
    {
        try
        {
            File f = new File("screen.jpg");
            PrintWriter out = new PrintWriter(_socket.getOutputStream(),true);
            out.println("SIZE" + f.length());
            
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("screen.jpg"));
            int i = 0;
            byte[] data = new byte[4*1000];
            
            BufferedOutputStream bos = new BufferedOutputStream (_socket.getOutputStream());
            i = bis.read(data);
            while (i != -1)
            {
                bos.write(data,0,i);
                i = bis.read(data);
            }
            bos.flush();
            
            System.out.println("Het file");
            
        }catch (Exception e){e.printStackTrace();}
    }
    public void run()
    {
        while(true)
        {
            try
            {
                chupAnhManHinh();
                guiAnhVeServer();
                Thread.sleep(3000);
            } catch (InterruptedException ex)
            {Logger.getLogger(SendToServer.class.getName()).log(Level.SEVERE,null,ex);}
        }
    }
}
