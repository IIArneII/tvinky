package com.company.model.client;

import com.company.model.Message;
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
            writeMsg = new ObjectOutputStream(socket.getOutputStream());
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
            Message message = new Message("addCharacter", connectionServer.client.character.copy());
//            writeMsg.writeObject(message.copy());
//            message.setType("character");
//
//            while(true){
//                Thread.currentThread().sleep(1);
//                message.setObject(connectionServer.client.character.copy());
//                writeMsg.writeObject(message.copy());
//            }
        }
        catch (Exception e) {
            try {
                socket.close();
            }
            catch (Exception ee){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
            System.out.println("Ошибка при отправке сообщения серверу: " + e.getMessage());
        }

    }
}
