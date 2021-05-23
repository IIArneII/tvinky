package com.company.model.client;

import com.company.model.entity.Character;
import com.company.model.game.Game;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ReadMsg extends Thread{
    Net n;
    Socket socket;
    static private ObjectInputStream input;
    private static java.io.InputStream in;
    Game game;
    public ReadMsg(Net n, Socket socket, Game game){
        System.out.println("ReadMSG");
        try {
            this.n = n;
            this.socket = socket;
            in = socket.getInputStream();
            input = new ObjectInputStream(in);
            this.game = game;
        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        System.out.println("RUN rEADmSG");
        while(true){
            try {
                game = (Game) input.readObject();
            }
            catch (Exception e) {}
        }
    }

}
