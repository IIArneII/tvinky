package com.company.model.entity;

import java.io.Serializable;

public class Entity implements Serializable {
    private double x;
    private double y;
    private double r;

    public Entity(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getR(){return this.r;}

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setR(double r){this.r = r;}

}
