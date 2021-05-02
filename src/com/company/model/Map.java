package com.company.model;

import java.util.ArrayList;

public class Map {
    private int[][] map;
    private ArrayList<Entity> entityStaticList;

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
    public void setMap(int x, int y, int value){ this.map[x][y] = value;}
    public ArrayList<Entity> getEntityStaticList(){return this.entityStaticList;}

}
