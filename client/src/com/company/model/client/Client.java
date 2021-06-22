package com.company.model.client;

import com.company.model.rendering.Rendering;
import com.company.model.game.Character;
import com.company.model.game.Game;

public class Client {
    private Game game;
    private Character character;
    private Movement movement;
    private Rendering rendering;
    private Connection connection;
    private boolean isConnection;
    public static UDPClient udpClient;
    private UDPClientRead udpClientRead;
    public static TimeOut timeOut;

    public Client(){
        isConnection = false;
        character = new Character("player");
        game = new Game(character);
        rendering = new Rendering(character, game);
        connection = null;
        movement = new Movement(character, game, connection);
        timeOut = new TimeOut(this);
    }

    public Client(String name, String ip, int port){
        isConnection = true;
        character = new Character(name);
        game = new Game(character);
        rendering = new Rendering(character, game);
        //connection = new Connection(game, ip, port);
        try {
            udpClient = new UDPClient(game, ip, port);
            udpClientRead = new UDPClientRead(game, ip, port);
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
        movement = new Movement(character, game, connection);
        timeOut = new TimeOut(this);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Movement getMovement(){
        return movement;
    }

    public Rendering getRendering(){
        return rendering;
    }

    public void start(){
        movement.start();
        rendering.start();
        if(isConnection){
            udpClient.start();
            udpClientRead.start();
            timeOut.start();
        }
    }

    public void pause(boolean pause){
        movement.setPause(pause);
        rendering.setRenderingOnPause(pause);
    }

    public void stop(){
        movement.stop();
        rendering.setLaunched(false);
        timeOut.launched = false;
        if(isConnection){
            connection.stop();
            udpClient.launched = false;
            udpClientRead.launched = false;
        }
    }
}
