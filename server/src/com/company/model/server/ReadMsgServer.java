package com.company.model.server;

import com.company.model.Message;
import com.company.model.entity.Character;
import com.company.model.game.Game;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ReadMsgServer extends Thread{
    ConnectionClient connectionClient;
    Socket socket;
    ObjectInputStream readMsg;

    public ReadMsgServer(ConnectionClient connectionClient, Socket socket){
        try {
            this.connectionClient = connectionClient;
            this.socket = socket;
            readMsg = new ObjectInputStream(socket.getInputStream());
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ReadMsgServer: " + e.getMessage());
        }
    }

    @Override
    public void run(){
        try {
            //Message message = new Message("game", connectionClient.server.getGame().copy());

//            connectionClient.writeMsgServer.writeMsg(message);
//
//            message = (Message) readMsg.readObject();
//            Character character = (Character)message.getObject();
//            System.out.println("Получен персонаж" + character.getName());
//            connectionClient.server.getGame().getEntityDynamicList().put(character.getName(), character);
//            connectionClient.server.writeMsgAll(new Message("addCharacter", message.getObject()));
//
//            message = (Message) readMsg.readObject();
            Message message = new Message();
            Character character = new Character();
            //if(message.getType().equals("info") & message.getComment().equals("yes")){
                //connectionClient.writeMsgServer.start();

            message = (Message) readMsg.readObject();
            if(message.getType().equals("character")){
                character = (Character) message.getObject();
                connectionClient.server.getGame().getEntityDynamicList().put(character.getName(), character);
                connectionClient.name = character.getName();
            }

            while (true){
                Thread.currentThread().sleep(1);
                message = (Message) readMsg.readObject();
                if(message.getType().equals("character")){
                    character = (Character) message.getObject();
                    connectionClient.server.getGame().getEntityDynamicList().put(character.getName(), character);
                }
            }
            //}
        }
        catch (Exception e) {
            try {
                socket.close();
            }
            catch (Exception ee){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
            connectionClient.disconnection();
            System.out.println("Ошибка при получении сообщения с клиента: " + e.getMessage());
        }

    }
}
