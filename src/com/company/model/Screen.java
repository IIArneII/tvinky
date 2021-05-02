package com.company.model;

public class Screen {
    private int[][] screen;

    public Screen(){
        this.screen = new int[800][5];
    }

    public int[][] getScreen(){return this.screen;}
    public void setScreen(int[][] screen){this.screen = screen;}
}
