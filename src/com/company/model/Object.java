package com.company.model;

public class Object {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int sprite;
    private boolean remove;
    private String type;

    public Object(double x, double y, int sprite, String type){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.vx = 0;
        this.vy = 0;
        this.remove = false;
        this.type = type;
    }

    public double getVx(){return this.vx;}
    public double getVy(){return this.vy;}

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy){
        this.vy = vy;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public String getType(){return this.type;}

    public void setRemove(boolean remove){
        this.remove = remove;
    }


}
