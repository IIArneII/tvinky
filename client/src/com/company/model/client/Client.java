package com.company.model.client;

import com.company.model.rendering.Rendering;
import com.company.model.entity.Character;
import com.company.model.game.Game;

public class Client {
    private Game game;
    private Character character;
    private Movement movement;
    private Rendering rendering;
    private Connection connectionServer;

    public Client(){
        character = new Character("player");
        game = new Game(character);
        movement = new Movement(character, game.getMap());
        rendering = new Rendering(character, game);
        connectionServer = null;
    }

    public Client(String name, String ip, int port){
        character = new Character(name);
        game = new Game(character);
        movement = new Movement(character, game.getMap());
        rendering = new Rendering(character, game);
        connectionServer = new Connection(this, ip, port);
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
        //connectionServer.start();
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
