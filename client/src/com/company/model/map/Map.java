package com.company.model.map;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable, Cloneable {
    private ArrayList<Wall> walls;

    public Map(){
        walls = new ArrayList<>();

        //при создании визуальных стен ставить координаты 0.55 больше или меньше
        walls.add(new Wall(7.55, 0, 7.55, 4.4, 0, 3, 1));// v1

        walls.add(new Wall(12, 0.1, -16.1, 0.1, 0, 3, 1)); //низ v2
        walls.add(new Wall(12, 4.4, -16.1, 4.4, 0, 3, 1)); //верх v2

        walls.add(new Wall(3.575, 1.4, 3.575, 4.401, 0, 3, 1)); // v3
        walls.add(new Wall(2.475, 1.4, 2.475, 4.401, 0, 3, 1)); // v3
        walls.add(new Wall(3.575, 1.4, 2.475, 1.4, 0, 3, 1)); // v3
    }

    public int isWall(double x, double y){
        double v1 = Math.abs(x-1+y-19) + Math.abs(x+1-y-10) + 1;
        double v2 = Math.abs(2*x-21.5+y+10.5)+Math.abs(2*x-13-y+10);
        double v3 = Math.abs(x-10-2*y)+Math.abs(x+15+2*y)-20;
        double v4 = Math.abs(0.7*x-13-2*y+22+7-2)+Math.abs(1.9*x-36+2*y+19+7-9)-6;
        double v5 = Math.abs(1.3*x+20-2*y+6)+Math.abs(x+15+2*y-20)-6;
        double v6 = Math.abs(2*x-10+11+y+13)+Math.abs(2*x-10+13-y+19);
        double v7 = Math.abs(2*x+y+32)+Math.abs(2*x-y+23);
        double v8 = Math.abs(x+30-2*y-21)+Math.abs(x+15+2*y+14) - 6;
        double v9 = Math.abs(x+21.5)+Math.abs(y-7);
        double v10 = Math.abs(x+3*y-24+20)+Math.abs(x-3*y+24+20);
        double v11 = Math.abs(x+y-3+9)+Math.abs(x-y-3-10);
        double v12 = Math.abs(x+y-15-10)+Math.abs(x-y+15-10);
        double v13 = Math.abs(x+y-16+4)+Math.abs(x-y+16+4);
        double v14 = Math.abs(x+21.5)+Math.abs(y-15.5);

        if(v1 < 13) return 1;
        if(v2<3) return 1;
        if(v3<3) return 1;
        if (v4<3) return 1;
        if(v5<3) return 1;
        if(v6 < 3) return 1;
        if(v7 < 8) return 1;
        if(v8 < 3) return 1;
        if(v9 < 3) return 1;
        if(v10 < 6) return 1;
        if(v11 > 50) return 1;
        if(v12 < 8) return 1;
        if(v13 < 5) return 1;
        if(v14 < 3) return 1;
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
