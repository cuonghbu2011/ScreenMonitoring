/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MonitorServer {
    /*
    public Hashtable lsClient;
    public BigForm _giaodien;
    Socket serverSocket;
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
              ListenToClient nghe = new ListenToClient(_socket, _giaodien);
              String tenThuMuc = soc.getInetAddress().getHostName();
              _giaodien.addClient(tenThuMuc+ "\\screen222.jpg");
              
              Thread t = new Thread((Runnable) nghe);
              t.start();
            }
        } catch(Exception e){e.printStackTrace();}
        
    }
*/
    private List<SendToClient> _lsClient;
    public BigForm _giaodien;
    
    public MonitorServer()
    {
        _lsClient = new ArrayList<SendToClient>();
    }
    
    public MonitorServer(BigForm giaodien){
        _giaodien = giaodien;
    }
    
    public void AddClient(SendToClient client){
        _lsClient.add(client);
        
        List<String> dsClient = new ArrayList<String>();
        dsClient = this.GetListClientIP();
        for (String string : dsClient) {
            System.out.println(string);
        }
        
        //Thong bao co client moi ket noi o day
    }
    
    public SendToClient GetClient(String clientIP){
        
        for(int i = 0; i < _lsClient.size(); i++){
            SendToClient client = _lsClient.get(i);
            if (client.GetClientIP().equals(clientIP)){
                return client;
            }
        }
        
        return null;
    }
    
    public void Send(String clientIP, String msg){
        SendToClient client = GetClient(clientIP);
        if (client != null){
            client.Send(msg);
        }
    }
    
    public void ShowGiaoDien(){
        //show giao dien
    }
    
    public void CapNhatGiaoDien(){
        
    }
    
    public List<String> GetListClientIP(){
        List<String> clientNames = new ArrayList<>();
        
        for (SendToClient sendToClient : _lsClient) {
            clientNames.add(sendToClient.GetClientIP());
        }
        
        return clientNames;
    }
}
