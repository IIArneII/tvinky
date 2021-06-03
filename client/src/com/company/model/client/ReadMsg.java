package com.company.model.client;

import com.company.model.Message;
import com.company.model.entity.Character;
import com.company.model.game.Game;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class ReadMsg extends Thread{
    Connection connectionServer;
    Socket socket;
    ObjectInputStream readMsg;
    Game game;

    public ReadMsg(Connection connectionServer, Socket socket, Game game){
        System.out.println("ReadMsg");
        try {
            this.connectionServer = connectionServer;
            this.socket = socket;
            InputStream in = socket.getInputStream();
            readMsg = new ObjectInputStream(in);
            this.game = game;
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ReadMsg: " + e.getMessage());
        }
    }

    @Override
    public void run(){
        System.out.println("RUN rEADmSG");
        HashMap<String, Character> characters;
        Message message;
        try {
            while(true){
                Thread.currentThread().sleep(1);
                message = (Message) readMsg.readObject();

                if(message.getType().equals("game")){
                    Game  game = (Game)message.getObject();
                    game.getEntityDynamicList().remove(connectionServer.client.getCharacter().getName());
                    connectionServer.client.getGame().updateFrom(game);
                }
            }
        }
        catch (Exception e) {
            try {
                socket.close();
            }
            catch (Exception ee){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
            System.out.println("Ошибка при получении сообщения с сервера: " + e.getMessage());
        }
    }

}
