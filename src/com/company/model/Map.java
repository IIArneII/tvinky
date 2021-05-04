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

    public int isWall(double x, double y){
        double v1 = (x - 5) * (x - 5) + (y - 5) * (y - 5);
        double v2 = (x - 5) * (x - 5) + (y / 100 + 2.95);
        if(v2 > 3){
            if(v1 < 30){
                return 0;
            }
            else return 2;
        }
        else return 1;
    }

    public ArrayList<Entity> getEntityStaticList(){return this.entityStaticList;}

}
