package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Character;
import com.company.model.game.Shot;

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
                if(message.getType().equals("shot")){
                    Shot shot = (Shot) message.getObject();
                    System.out.println(shot.getSection().getA().getX() + "   " + shot.getSection().getA().getY());
                    //connectionClient.server.getProcess().addShot(shot);
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
            connectionClient.disconnection();
            System.out.println("Ошибка при получении сообщения с клиента: " + e.getMessage());
        }
    }
}
