package com.company.model.map;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable, Cloneable {
    private ArrayList<Wall> walls;

    public Map(){
        walls = new ArrayList<>();

        walls.add(new Wall(2, 0, 0, 2, 0, 3, 1));
    }

    public int isWall(double x, double y){
        double v1 = (x - 5) * (x - 5) + (y - 5) * (y - 5);
        if(v1 > 500) return 1;
        return 0;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void addWall(Wall wall){
        walls.add(wall);
    }

    public Map copy(){
        Map temp = new Map();
        temp.walls = (ArrayList<Wall>)walls.clone();
        return temp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
