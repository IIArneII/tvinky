package com.company.model;

public class Map {
    private int[][] map;

    public Map(){
        this.map = new int[][]{ {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 1, 2, 3, 4, 0, 0, 0, 0, 0, 4},
                                {4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4}
                               };
    }

    public int[][] getMap(){ return map;}
    public void setMap(int x, int y, int value){
        this.map[x][y] = value;
    }

}
