package com.company.model;

public class Adapter {
    public static void run(){
        System.out.println("com.company.model.Adapter.run()");
    }

    public static double[][] getWindow(){
        System.out.println("com.company.model.Adapter.getWindow()");
        return new double[0][0];
    }

    public static void pressW(){
        System.out.println("com.company.model.Adapter.pressW()");
    }

    public static void pressS(){
        System.out.println("com.company.model.Adapter.pressS()");
    }

    public static void pressD(){
        System.out.println("com.company.model.Adapter.pressD()");
    }

    public static void pressA(){
        System.out.println("com.company.model.Adapter.pressA()");
    }

    public static void pressRight(){
        System.out.println("com.company.model.Adapter.pressRight()");
    }

    public static void pressLeft(){
        System.out.println("com.company.model.Adapter.pressLeft()");
    }

    public static void pressShot(){
        System.out.println("com.company.model.Adapter.pressShot()");
    }

    public static void setName(String name){
        System.out.println("com.company.model.Adapter.setName()");
    }

    public static void setMode(boolean mode){
        System.out.println("com.company.model.Adapter.setMode()");
    }
}
