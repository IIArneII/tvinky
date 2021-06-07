package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Game;

import java.net.Socket;

public class Connection{
    private Socket socket;
    private Game game;
    private ReadMsg readMsg;
    private WriteMsg writeMsg;

    public Connection(Game game, String ip, int port) {
        try {
            this.game = game;
            socket = new Socket(ip, port);
            writeMsg = new WriteMsg(socket, game);
            readMsg = new ReadMsg(socket, game);
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ConnectionServer: " + e.getMessage());
        }
    }

    public void start(){
        readMsg.start();
        writeMsg.start();
    }

    public void stop(){
        readMsg.setLaunched(false);
        writeMsg.setLaunched(false);
    }

    public void write(Message message) throws Exception{
        writeMsg.write(message);
    }
}
