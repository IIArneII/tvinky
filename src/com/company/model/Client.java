package com.company.model;

public class Client {
    public int STOP = 0;
    public int UP = 0;
    public int DOWN = 0;
    public int TurnLEFT = 0;
    public int TurnRIGHT = 0;
    public int SHOOT = 0;

    GameClient gameClient;
    Movement movement;

    public Client(){
        gameClient = new GameClient();
        movement = new Movement(this);
    }

    public void start(){
        movement.start();
    }
}
