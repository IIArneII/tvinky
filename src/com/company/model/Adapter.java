package com.company.model;

public class Adapter {

    private Client client;

    public Adapter(){
        client = new Client();
    }

    public void start(){
        client.start();
    }

    public Screen getScreen(){
        return client.gameClient.getRendering().getScreen();
    }

    public void pressW(){
        System.out.println("com.company.model.Adapter.pressW()");
        client.UP = 1;
    }

    public void pressWReleased(){
        System.out.println("com.company.model.Adapter.pressW()");
        client.UP = 0;
    }

    public void pressS(){
        System.out.println("com.company.model.Adapter.pressS()");
        client.DOWN = 1;
    }

    public void pressSReleased(){
        System.out.println("com.company.model.Adapter.pressS()");
        client.DOWN = 0;
    }

    public void pressD(){
        System.out.println("com.company.model.Adapter.pressD()");
    }

    public void pressA(){
        System.out.println("com.company.model.Adapter.pressA()");
    }

    public void pressRight(){
        System.out.println("com.company.model.Adapter.pressRight()");
        client.TurnRIGHT = 1;
    }

    public void pressRightReleased(){
        System.out.println("com.company.model.Adapter.pressRight()");
        client.TurnRIGHT = 0;
    }

    public void pressLeft(){
        System.out.println("com.company.model.Adapter.pressLeft()");
        client.TurnLEFT = 1;
    }

    public void pressLeftReleased(){
        System.out.println("com.company.model.Adapter.pressLeft()");
        client.TurnLEFT = 0;
    }

    public void pressShot(){
        System.out.println("com.company.model.Adapter.pressShot()");
        client.SHOOT = 1;
    }

    public void pressShotReleased(){
        System.out.println("com.company.model.Adapter.pressShot()");
        client.SHOOT = 0;
    }

    public void setName(String name){
        System.out.println("com.company.model.Adapter.setName()");
    }

    public void setMode(boolean mode){
        System.out.println("com.company.model.Adapter.setMode()");
    }
}
