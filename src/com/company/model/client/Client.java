package com.company.model.client;

import com.company.model.rendering.Rendering;
import com.company.model.entity.Character;
import com.company.model.game.Game;

public class Client {
    private Game game;
    public Character character;
    private Movement movement;
    private Rendering rendering;
    private ConnectionServer connectionServer;

    public Client(){
        System.out.println("Client");
        character = new Character("4eis");
        game = new Game(character);
        movement = new Movement(character, game.getMap());
        rendering = new Rendering(character, game);
        connectionServer = new ConnectionServer(this, "26.24.57.118", 1111);
    }

    public Game getGame() {
        //System.out.println("getGame");
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Movement getMovement(){
        //System.out.println("getMovement");
        return movement;
    }

    public Rendering getRendering(){
        //System.out.println("getRendering");
        return rendering;
    }

    public void start(){
        movement.start();
        rendering.start();
        connectionServer.start();
    }

    public void pause(boolean pause){
        movement.setPause(pause);
        rendering.setRenderingOnPause(pause);
    }

    public void stop(){
        movement.setLaunched(false);
        rendering.setRenderingLaunched(false);
    }
}
