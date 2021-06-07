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
            Message message;
            Character character;

            message = (Message) readMsg.readObject();
            if(message.getType().equals("character")){
                character = (Character) message.getObject();
                connectionClient.server.getGame().getEntityDynamicList().put(character.getName(), character);
                connectionClient.name = character.getName();
                try {
                    connectionClient.server.getDb().DataInput(character.getName());
                }
                catch (Exception e){
                    System.out.println("Ошибка при запросе к базе данных: " + e.getMessage());
                }
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
                    System.out.println(shot.getCharacter().getX() + "   " + shot.getSection().getA().getY());
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
