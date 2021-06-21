package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Character;
import com.company.model.game.Shot;
import com.company.model.map.Wall;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Connection {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String playerName;

    public Connection(Socket socket) {
        try {
            playerName = "";
            this.socket = socket;
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readMsg();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writeMsg();
                }
            }).start();
        }
        catch (Exception e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }

    private void readMsg(){
        try {
            Message message;
            Character character;

            message = (Message) in.readObject();
            if(message.getType().equals("check")){
                write(new Message("check", ""));
            }
            if(message.getType().equals("character")){
                character = (Character) message.getObject();
                Server.game.addCharacter(character);
                //Server.game.getEntityDynamicList().put(character.getName(), character);
                playerName = character.getName();
                try {
                    Server.db.DataInput(character.getName());
                }
                catch (Exception e){
                    System.out.println("Ошибка при запросе к базе данных: " + e.getMessage());
                }
            }

            while (true){
                Thread.currentThread().sleep(1);
                message = (Message) in.readObject();
                if(message.getType().equals("character")){
                    character = (Character) message.getObject();
                    //Server.game.getEntityDynamicList().put(character.getName(), character);
                    Server.game.updateCharacter(character);
                }
                if(message.getType().equals("shot")){
                    Shot shot = (Shot) message.getObject();
                    character = Server.game.shotProcessing(shot);

                    if(character != null) Server.writeMsgOne(new Message("changeXY", character.copy()), playerName);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при получении сообщения с клиента: " + e.getMessage());
        }
        finally {
            try {
                socket.close();
            }
            catch (Exception e){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
            disconnection();
        }
    }

    private void writeMsg(){
        try {
            while (true){
                Thread.currentThread().sleep(1);
                write(new Message("game", Server.game.copy()));
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при отправке сообщения клиенту: " + e.getMessage());
        }
        finally {
            try {
                socket.close();
            }
            catch (Exception e){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
        }
    }

    synchronized public void write(Message message) throws Exception{
        out.writeObject(message);
    }

    public void disconnection(){
        Server.game.getEntityDynamicList().remove(playerName);
        Server.connections.remove(this);
        System.out.println("Разрыв соединения с клиентом");
    }

    public String getPlayerName() {
        return playerName;
    }
}
