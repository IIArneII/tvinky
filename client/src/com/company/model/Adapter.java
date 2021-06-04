package com.company.model;

import com.company.model.client.Client;
import com.company.model.rendering.Screen;

public class Adapter {

    private Client client;

    public Adapter(){
        client = new Client();
    }

    public Adapter(String name, String ip, int port){
        if(!name.equals("") && !ip.equals(""))
        client = new Client(name, ip, port);
        else client = new Client();
    }

    public void startClient(){
        client.start();
    }

    public void stop(){
        client.stop();
    }

    public void pause(boolean pause){
        client.pause(pause);
    }

    public Screen getScreen(){
        return client.getRendering().getScreen();
    }

    public void pressW(){
        client.getMovement().setForth(true);
        client.getMovement().setBackForthEvent(true);
    }

    public void pressWReleased(){
        client.getMovement().setForth(false);
        //client.getMovement().setBackForthEvent(false);
    }

    public void pressS(){
        client.getMovement().setBack(true);
        client.getMovement().setBackForthEvent(true);
    }

    public void pressSReleased(){
        client.getMovement().setBack(false);
        //client.getMovement().setBackForthEvent(false);
    }

    public void pressD(){
        client.getMovement().setRight(true);
        client.getMovement().setRightLeftEvent(true);
    }

    public void pressDReleased() {
        client.getMovement().setRight(false);
        //client.getMovement().setRightLeftEvent(false);
    }

    public void pressA(){
        client.getMovement().setLeft(true);
        client.getMovement().setRightLeftEvent(true);
    }

    public void pressAReleased() {
        client.getMovement().setLeft(false);
        //client.getMovement().setRightLeftEvent(false);
    }

    public void pressRight(){
        client.getMovement().setTurnRight(true);
        client.getMovement().setTurnRightLeftEvent(true);
    }

    public void pressRightReleased(){
        client.getMovement().setTurnRight(false);
        //client.getMovement().setTurnRightLeftEvent(false);
    }

    public void pressLeft(){
        client.getMovement().setTurnLeft(true);
        client.getMovement().setTurnRightLeftEvent(true);
    }

    public void pressLeftReleased(){
        client.getMovement().setTurnLeft(false);
        //client.getMovement().setTurnRightLeftEvent(false);
    }

    public void pressShot(){
        client.getMovement().setShotEvent(true);
        client.getMovement().setShot(true);
    }

    public void pressShotReleased(){
        client.getMovement().setShot(false);
        client.getMovement().setShotEvent(false);
    }

    public void setName(String name){
        System.out.println("com.company.model.Adapter.setName()");
    }

    public void setMode(boolean mode){
        System.out.println("com.company.model.Adapter.setMode()");
    }
}
