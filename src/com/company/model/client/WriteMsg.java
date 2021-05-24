package com.company.model.client;

import com.company.model.entity.Character;
import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteMsg extends Thread{
    ConnectionServer connectionServer;
    Socket socket;
    ObjectOutputStream writeMsg;
    Game game;

    public WriteMsg(ConnectionServer connectionServer, Socket socket, Game game){
        System.out.println("WriteMsg");
        try {
            this.connectionServer = connectionServer;
            this.socket = socket;
            System.out.println("Получение потока оутпута");
            writeMsg = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Получение потока оутпута");
            this.game = game;
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании WriteMsg: " + e.getMessage());
        }
    }
    @Override
    public void run(){
        System.out.println("run WriteMsg");
        try {
            Character temp;
            while(true){
                Thread.currentThread().sleep(1);
                temp = connectionServer.client.character.copy();
                writeMsg.writeObject(temp);
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при отправке сообщения серверу: " + e.getMessage());
        }

    }
}
