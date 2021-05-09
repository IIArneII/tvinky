package com.company.model;

import com.company.model.client.GameClient;

public class Client {
    public int STOP = 0;
    public int UP = 0;
    public int DOWN = 0;
    public int LEFT = 0;
    public int RIGHT = 0;
    public int TurnLEFT = 0;
    public int TurnRIGHT = 0;
    public int SHOOT = 0;

    GameClient gameClient;
    Movement movement;

    public Client(){
        gameClient = new GameClient();
        movement = new Movement(this);
        System.out.println("Rrecec");
    }

    public void start(){
        movement.start();
        gameClient.startRendering();
    }

    public void pause(boolean pause){
        gameClient.pauseRendering(pause);
    }

    public void stop(){
        gameClient.stopRendering();
        movement.setMovementLaunched(false);
    }
}
