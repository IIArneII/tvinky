package com.company.model.client;

import com.company.model.entity.Character;
import com.company.model.game.Game;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class ReadMsg extends Thread{
    ConnectionServer connectionServer;
    Socket socket;
    ObjectInputStream readMsg;
    Game game;

    public ReadMsg(ConnectionServer connectionServer, Socket socket, Game game){
        System.out.println("ReadMsg");
        try {
            this.connectionServer = connectionServer;
            this.socket = socket;
            System.out.println("Получение потока инпута");
            InputStream in = socket.getInputStream();
            System.out.println("Получен потока инпута");
            readMsg = new ObjectInputStream(in);
            System.out.println("Получение обжект инпут стрим");
            this.game = game;
            System.out.println("ReadMsg end");
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ReadMsg: " + e.getMessage());
        }
    }

    @Override
    public void run(){
        System.out.println("RUN rEADmSG");
        HashMap<String, Character> temp;
        int n = 0;
        try {
            while(true){
                Thread.currentThread().sleep(1);
                temp = (HashMap<String, Character>) readMsg.readObject();
                temp.remove(connectionServer.client.character.getName());
                game.getEntityDynamicList().putAll(temp);
                if(temp.size() > n){
                    n++;
                    connectionServer.client.getGame().getMap().getWalls().add(temp.get("ddd").getWall());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при получении сообщения с сервера: " + e.getMessage());
        }
    }

}
