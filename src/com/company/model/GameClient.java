package com.company.model;

public class GameClient extends Game{
    private Rendering rendering;

    public GameClient(){
        this.rendering = new Rendering(this);
    }

}
