package com.company.model;

public class GameClient extends Game{
    private Rendering rendering;

    public GameClient(){
        this.rendering = new Rendering(this);
    }

    public void startRendering(){
        rendering.start();
    }

    public void stopRendering(){
        rendering.setRenderingLaunched(false);
    }

    public void pauseRendering(boolean pause){
        rendering.setRenderingOnPause(pause);
    }

    public Rendering getRendering(){ return this.rendering;}

}
