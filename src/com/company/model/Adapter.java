package com.company.model;

import com.company.model.client.Client;
import com.company.model.rendering.Screen;

public class Adapter {

    private Client client;

    private int renderingMethod;

    public Adapter(){
        client = new Client();
        renderingMethod = 0;
    }

    public void setRenderingMethod(int renderingMethod) {
        this.renderingMethod = renderingMethod;
        client.getGameClient().getRendering().setRenderingMethod(renderingMethod);
    }

    public int getRenderingMethod() {
        return renderingMethod;
    }

    public void start(){
        client.start();
    }

    public void stop(){
        client.stop();
    }

    public void pause(boolean pause){
        client.pause(pause);
    }

    public Screen getScreen(){
        return client.getGameClient().getRendering().getScreen();
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
