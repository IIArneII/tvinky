package com.company.model.server;

import java.io.ObjectInputStream;
import java.net.Socket;
public class InputStream extends Thread{
    Connect c;
    Socket socket;
    static private ObjectInputStream input;
    private static java.io.InputStream in;
    Character readData;

    public InputStream(Connect c, Socket socket){
        try {
            this.c = c;
            this.socket = socket;
            in = socket.getInputStream();
            input = new ObjectInputStream(in);

        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        while(true){
            try {
                readData = (Character) input.readObject();
            }
            catch (Exception e) {}
        }
    }
}
