package com.company.model;

public class Section {
    private double aX, aY, bX, bY;

    public Section(){
        aX = 0;
        aY = 0;
        bX = 0;
        bY = 0;
    }

    public Section(double aX, double aY, double bX, double bY){
        this.aX = aX;
        this.aY = aY;
        this.bX = bX;
        this.bY = bY;
    }

    public double getAX() {
        return aX;
    }

    public double getAY() {
        return aY;
    }

    public double getBX() {
        return bX;
    }

    public double getBY() {
        return bY;
    }
}
