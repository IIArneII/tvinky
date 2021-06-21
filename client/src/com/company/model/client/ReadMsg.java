package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Character;
import com.company.model.game.Game;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class ReadMsg extends Thread{
    private boolean launched;
    private Socket socket;
    private ObjectInputStream in;
    private Game game;

    public ReadMsg(Socket socket, Game game){
        try {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            this.game = game;
            launched = false;
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ReadMsg: " + e.getMessage());
        }
    }

    @Override
    public void run(){
        launched = true;
        Message message;
        try {
            while(launched){
                this.sleep(1);
                message = (Message) in.readObject();

                if(message.getType().equals("game")){
                    Game  gameTemp = (Game)message.getObject();
                    gameTemp.getEntityDynamicList().remove(game.getCharacter().getName());
                    game.updateFrom(gameTemp);
                }
                if(message.getType().equals("changeXY")){
                    System.out.println("changeXY");
                    Character character = (Character)message.getObject();
                    game.getEntityDynamicList().get(character.getName()).updateFrom(character);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при получении сообщения с сервера: " + e.getMessage());
        }
        finally {
            try {
                socket.close();
            } catch (Exception e){
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
        }
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public boolean isLaunched() {
        return launched;
    }
}
