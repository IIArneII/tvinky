package com.company.model.server;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class OutputStream extends Thread{
    Connect c;
    Socket socket;
    static private ObjectOutputStream output;
    private static java.io.OutputStream out;
    Character writeData;

    public OutputStream(Connect c, Socket socket){
        try {
            this.c = c;
            this.socket = socket;
            out = socket.getOutputStream();
            output = new ObjectOutputStream(out);

        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        while(true){
            try {
                output.writeObject();
            }
            catch (Exception e) {}
        }
    }
}
