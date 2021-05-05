package com.company.model.map;

public class WallPoint {
    private double distance;
    private int color;

    public WallPoint(){
        distance = 0;
        color = 0;
    }

    public WallPoint(double distance, int color){
        this.distance = distance;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
