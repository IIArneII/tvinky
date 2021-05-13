package com.company.model.client;

import com.company.model.rendering.Rendering;
import com.company.model.entity.Character;
import com.company.model.game.Game;

public class Client {
    private Game game;
    private Character character;
    private Movement movement;
    private Rendering rendering;

    public Client(){
        character = new Character("Player");
        game = new Game(character);
        movement = new Movement(character, game.getMap());
        rendering = new Rendering(character, game.getMap());
    }

    public Game getGame() {
        return game;
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
