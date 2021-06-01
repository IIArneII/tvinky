package com.company.model.map;

public class WallPoint {
    private double distance;
    private int color;
    private int textureID;
    private double textureK;

    public WallPoint(){
        distance = 0;
        color = 0;
        textureID = 0;
        textureK = 0;
    }

    public WallPoint(double distance, int color, int textureID, double textureK){
        this.distance = distance;
        this.color = color;
        this.textureID = textureID;
        this.textureK = textureK;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public double getTextureK() {
        return textureK;
    }

    public void setTextureK(double textureK) {
        this.textureK = textureK;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
