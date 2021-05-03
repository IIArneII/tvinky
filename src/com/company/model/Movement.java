package com.company.model;

public class Movement {
    Client client;
    BackForthListener backForthListener;
    TurnRightLeftListiner turnRightLeftListiner;

    public Movement(Client client){
        this.client = client;
        backForthListener = new BackForthListener(this);
        turnRightLeftListiner = new TurnRightLeftListiner(this);

    }

}
