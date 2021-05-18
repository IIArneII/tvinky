package com.company.model.server;

import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class OutputStream extends Thread{
    Connect c;
    Socket socket;
    static private ObjectOutputStream output;
    private static java.io.OutputStream out;
    Game game;
    public OutputStream(Connect c, Socket socket, Game game){
        try {
            this.c = c;
            this.socket = socket;
            out = socket.getOutputStream();
            output = new ObjectOutputStream(out);
            this.game = game;
        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        while(true){
            try {
                output.writeObject(game);
            }
            catch (Exception e) {}
        }
    }
}
