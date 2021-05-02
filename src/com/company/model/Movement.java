package com.company.model;

public class Movement {
    Client client;
    BackForthListener backForthListener;

    public Movement(Client client){
        this.client = client;
        backForthListener = new BackForthListener(this);
    }

}
