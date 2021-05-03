package com.company.model;

public class Movement {
    Client client;
    BackForthListener backForthListener;
    RightLeftListiner rightLeftListiner;
    TurnRightLeftListiner turnRightLeftListiner;
    ShotListener shotListener;

    public Movement(Client client){
        this.client = client;
        this.backForthListener = new BackForthListener(this);
        this.rightLeftListiner = new RightLeftListiner(this);
        this.turnRightLeftListiner = new TurnRightLeftListiner(this);
        this.shotListener = new ShotListener(this);
    }

    public void start(){
        backForthListener.start();
        rightLeftListiner.start();
        turnRightLeftListiner.start();
        shotListener.start();
    }

}
