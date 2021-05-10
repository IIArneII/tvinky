package com.company.model.client;

import com.company.model.rendering.Rendering;

public class Client {
    private GameClient gameClient;
    private Movement movement;
    private Rendering rendering;

    public Client(){
        gameClient = new GameClient();
        movement = new Movement(gameClient.getEntityDynamicList().get("player"), gameClient.getMap());
        rendering = new Rendering(gameClient.getEntityDynamicList().get("player"), gameClient.getMap());
    }

    public GameClient getGameClient() {
        return gameClient;
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
