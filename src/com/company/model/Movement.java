package com.company.model;

public class Movement {
    Client client;
    BackForthListener backForthListener;
    TurnRightLeftListiner turnRightLeftListiner;
    ShotListener shotListener;

    public Movement(Client client){
        this.client = client;
        this.backForthListener = new BackForthListener(this);
        this.turnRightLeftListiner = new TurnRightLeftListiner(this);
        this.shotListener = new ShotListener(this);
    }

    public void start(){
        backForthListener.start();
        turnRightLeftListiner.start();
        shotListener.start();
    }

}
