package com.company.model;

import java.util.ArrayList;

public class Map {
    private int[][] map;
    private ArrayList<Entity> entityStaticList;

    public Map(){
        this.map = new int[][]{ {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4}
                               };
    }

    public int[][] getMap(){ return map;}

    public void setMap(int x, int y, int value){ this.map[x][y] = value;}

    public int isWall(double x, double y){
        double v1 = (x - 5) * (x - 5) + (y - 5) * (y - 5);
        double v2 = (x - 5) * (x - 5) + y;
        double v3 = Math.abs(x-3) + Math.abs(y*2-6);
        double v4 = Math.sin(x*2-2) + Math.sin(y*2-2);
        double v5 = Math.abs(x*2) + Math.abs(x-1) + (2* Math.sin(y*x) + Math.abs(y-4))/(Math.sqrt(3));
        if(v1 > 50){
            return 1;
        }
        if(v2<3)
        {
            return 2;
        }
        if(v4>1.99){
            return 3;
        }
        if(v5 < 3){
            return 4;
        }
        return 0;
    }

    public ArrayList<Entity> getEntityStaticList(){return this.entityStaticList;}

}
