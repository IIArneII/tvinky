package com.company.model.entity;

import java.io.Serializable;

public class Entity implements Serializable {
    private double x;
    private double y;
    private double r;
    private int spriteID;

    public Entity(double x, double y, double r, int spriteID){
        this.x = x;
        this.y = y;
        this.r = r;
        this.spriteID = spriteID;
    }

    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getR(){return this.r;}
    public int getSpriteID(){return this.spriteID;}

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setR(double r){this.r = r;}
    public void setSpriteID(int spriteID){this.spriteID = spriteID;}

}
