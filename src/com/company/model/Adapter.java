package com.company.model;

public class Adapter {

    private RayCasting rc;

    public Adapter(){
        rc = new RayCasting();
    }

    public void run(){
        rc.run();
        System.out.println("com.company.model.Adapter.run()");
    }

    public double[][] getWindow(){
        System.out.println("com.company.model.Adapter.getWindow()");

        return rc.getScreen();
    }

    public void pressW(){
        System.out.println("com.company.model.Adapter.pressW()");
        rc.setEventCharacter(1);
    }

    public void pressS(){
        System.out.println("com.company.model.Adapter.pressS()");
        rc.setEventCharacter(2);
    }

    public void pressD(){
        System.out.println("com.company.model.Adapter.pressD()");
    }

    public void pressA(){
        System.out.println("com.company.model.Adapter.pressA()");
    }

    public void pressRight(){
        System.out.println("com.company.model.Adapter.pressRight()");
        rc.setEventCharacter(4);
    }

    public void pressLeft(){
        System.out.println("com.company.model.Adapter.pressLeft()");
        rc.setEventCharacter(3);
    }

    public void pressShot(){
        System.out.println("com.company.model.Adapter.pressShot()");
    }

    public void setName(String name){
        System.out.println("com.company.model.Adapter.setName()");
    }

    public void setMode(boolean mode){
        System.out.println("com.company.model.Adapter.setMode()");
    }
}
