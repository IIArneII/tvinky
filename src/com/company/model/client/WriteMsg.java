package com.company.model.client;

import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteMsg extends Thread{
    Net n;
    Socket socket;
    static private ObjectOutputStream output;
    private static java.io.OutputStream out;
    Game game;
    public WriteMsg(Net n, Socket socket, Game game){
        try {
            this.n = n;
            this.socket = socket;
            out = socket.getOutputStream();
            output = new ObjectOutputStream(out);
            this.game = game;
        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        System.out.println("run WriteMsg");
        while(true){
            try {
                output.writeObject(n.client.character);
            }
            catch (Exception e) {}
        }
    }
}
