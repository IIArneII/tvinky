package com.company.model;

public class Adapter {

    private Client client;

    public Adapter(){
        client = new Client();
    }

    public void setRenderingAdapter(RenderingAdapterInterface renderingAdapter){
        client.setRenderingAdapter(renderingAdapter);
    }

    public void start(){
        client.start();
    }

    public Screen getScreen(){
        return client.gameClient.getRendering().getScreen();
    }

    public void pressW(){
        client.UP = 1;
    }

    public void pressWReleased(){
        client.UP = 0;
    }

    public void pressS(){
        client.DOWN = 1;
    }

    public void pressSReleased(){
        client.DOWN = 0;
    }

    public void pressD(){
        client.RIGHT = 1;
    }

    public void pressDReleased() {
        client.RIGHT = 0;
    }

    public void pressA(){
        client.LEFT = 1;
    }

    public void pressAReleased() {
        client.LEFT = 0;
    }

    public void pressRight(){
        client.TurnRIGHT = 1;
    }

    public void pressRightReleased(){
        client.TurnRIGHT = 0;
    }

    public void pressLeft(){
        client.TurnLEFT = 1;
    }

    public void pressLeftReleased(){
        client.TurnLEFT = 0;
    }

    public void pressShot(){
        client.SHOOT = 1;
    }

    public void pressShotReleased(){
        client.SHOOT = 0;
    }

    public void setName(String name){
        System.out.println("com.company.model.Adapter.setName()");
    }

    public void setMode(boolean mode){
        System.out.println("com.company.model.Adapter.setMode()");
    }
}
