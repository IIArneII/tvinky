package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Character;
import com.company.model.game.Shot;
import com.company.model.map.Wall;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String PlayerName;

    public Connection(Socket socket) {
        try {
            PlayerName = "";
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
                Server.game.getEntityDynamicList().put(character.getName(), character);
                PlayerName = character.getName();
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
                    Server.game.getEntityDynamicList().put(character.getName(), character);
                }
                if(message.getType().equals("shot")){
                    Shot shot = (Shot) message.getObject();
                    new Thread(() -> {
                        Wall wall = new Wall(shot.getSection(), 1, 1, 1);
                        Server.game.getMap().addWall(wall);
                        try {
                            Thread.currentThread().sleep(3000);
                            Server.game.getMap().getWalls().remove(wall);
                            Character temp = Server.game.getEntityDynamicList().get(PlayerName).copy();
                            temp.setX(0);
                            temp.setY(0);
                            write(new Message("changeXY", temp));
                        }
                        catch (Exception e){}
                    }).start();
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
        Server.game.getEntityDynamicList().remove(PlayerName);
        Server.connections.remove(this);
        System.out.println("Разрыв соединения с клиентом");
    }
}
