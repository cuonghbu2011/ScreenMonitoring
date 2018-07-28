/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Models.Request;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Models.RequestImageFile;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class SendToServer implements Runnable{
    
    private Socket _socket;
    
    public SendToServer(Socket s){
        _socket = s;
    }
    
    private String folder = "ScreenShots\\";
    public String chupAnhManHinh()
    {
        try
        {
            File file = new File(folder);
            if (!file.exists())
            {
                file.mkdir();
            }
            String fileName = getCurrentTimeStamp() + ".jpg";
            String filePath = folder + fileName;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);
            ImageIO.write(image,"jpg", new File(filePath));
            System.out.println("Da luu vao " + filePath + ".");
            return fileName;
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    
    public void guiAnhVeServer() throws IOException
    {
        try
        {
            File f = new File("screen.jpg");
            PrintWriter out = new PrintWriter(_socket.getOutputStream(),true);
            //out.println("SIZE" + f.length());
            
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("screen.jpg"));
            int i = 0;
            byte[] data = new byte[(int)f.length()];
            
            BufferedOutputStream bos = new BufferedOutputStream (_socket.getOutputStream());
            i = bis.read(data);
            while (i != -1)
            {
                bos.write(data,0,i);
                i = bis.read(data);
            }
            bos.flush();
            bos.close();
            out.close();
            bis.close();
            _socket.close();
            System.out.println("Het file");
        }catch (Exception e){
            _socket.close();
            e.printStackTrace();
        }
    }
    
    public void guiAnhVeServer1(String name) throws IOException
    {
        try
        {
            if (name == ""){
                return;
            }
            File f = new File(folder + name);
            
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            byte[] data = new byte[(int)f.length()];
            bis.read(data);
            
            ObjectOutputStream oOS = new ObjectOutputStream(_socket.getOutputStream());
            
            RequestImageFile sf = new RequestImageFile();
            sf.Content = data;
            sf.Length = f.length();
            sf.Type = ".jpg";
            sf.Name = name;
            
            oOS.writeObject(sf);

            oOS.flush();
            //oOS.close();
            //bis.close();
            
            //_socket.close();
            System.out.println("Het file");
        }catch (Exception e){
            _socket.close();
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                try {
                    guiAnhVeServer1(chupAnhManHinh());
                } catch (IOException ex) {
                    Logger.getLogger(SendToServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                Thread.sleep(10000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(SendToServer.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
}
