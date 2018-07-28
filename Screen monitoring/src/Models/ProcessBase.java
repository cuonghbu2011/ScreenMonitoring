/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.net.Socket;

/**
 *
 * @author X230
 */
public class ProcessBase implements IProcess{
    
    protected IRequest _request;
    protected Socket _socket;
    
    public ProcessBase(IRequest request, Socket socket){
        _request = request;
        _socket = socket;
    }

    @Override
    public void Process() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
