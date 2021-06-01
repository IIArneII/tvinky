package com.company.model.client;

import com.company.model.Message;
import com.company.model.entity.Character;
import com.company.model.game.Game;

import javax.crypto.spec.PSource;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
                    game.getEntityDynamicList().remove(connectionServer.client.character.getName());
                    connectionServer.client.getGame().updateFrom(game);
//                    connectionServer.writeMsg.writeMsg.writeObject(new Message("addCharacter", connectionServer.client.character));
//                    message = (Message) readMsg.readObject();
//                    Character character = (Character)message.getObject();
//                    if(message.getType().equals("addCharacter") & character.getName().equals(connectionServer.client.character.getName())){
//                        connectionServer.writeMsg.writeMsg.writeObject(new Message("info", "yes", ""));
//                    }
//                    connectionServer.writeMsg.start();
                }

//                if(message.getType().equals("addCharacter")){
//                    System.out.println("addCharacter");
//                    Character character = (Character)message.getObject();
//                    if(!character.getName().equals(connectionServer.client.character.getName())){
//                        connectionServer.client.getGame().addCharacter(character);
//                    }
//                }
//
//                if(message.getType().equals("characters")){
//                    characters = (HashMap<String, Character>)message.getObject();
//                    characters.remove(connectionServer.client.character.getName());
//                    connectionServer.client.getGame().updateCharacters(characters);
//                }
//
//                if(message.getType().equals("game")){
//                    Game  game = (Game)message.getObject();
//                    game.getEntityDynamicList().remove(connectionServer.client.character.getName());
//                    connectionServer.client.getGame().updateFrom(game);
//                }
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
