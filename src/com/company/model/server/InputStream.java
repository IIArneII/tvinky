package com.company.model.server;
import com.company.model.game.Game;
import java.io.ObjectInputStream;
import java.net.Socket;
import com.company.model.entity.Character;
public class InputStream extends Thread{
    Connect c;
    Socket socket;
    static private ObjectInputStream input;
    private static java.io.InputStream in;
    Character readData;
    Game game;
    public InputStream(Connect c, Socket socket, Game game){
        try {
            this.c = c;
            this.socket = socket;
            in = socket.getInputStream();
            input = new ObjectInputStream(in);
            this.game = game;
        }
        catch (Exception e) {}
    }
    @Override
    public void run(){
        while(true){
            try {
                readData = (Character) input.readObject();
                game.getEntityDynamicList().put(readData.getName(), readData);
            }
            catch (Exception e) {}
        }
    }
}
