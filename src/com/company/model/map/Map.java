package com.company.model.map;

import com.company.model.Entity;
import com.company.model.Section;

import java.util.ArrayList;

public class Map {
    //private int[][] map;
    private ArrayList<Entity> entityStaticList;
    private ArrayList<Wall> walls;

    public Map(){
        walls = new ArrayList<>();
        walls.add(new Wall(2, 2, 4, 4, 4));
        walls.add(new Wall(0, 0, 10, 0, 0));
        walls.add(new Wall(10, 0, 10, 10, 1));
        walls.add(new Wall(10, 10, 0, 10, 2));
        walls.add(new Wall(5, 10, 0, 0, 3));
        /*this.map = new int[][]{ {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                {4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4}
                               };*/
    }

    //public int[][] getMap(){ return map;}

    //public void setMap(int x, int y, int value){ this.map[x][y] = value;}

    public WallPoint distance(Section section, int rad){
        for(int i = 0; i < this.walls.size(); i++){
            Section temp = walls.get(i).getSection();
            double z1 = (section.getBX() - section.getAX()) * (temp.getAY() - section.getAY()) - (temp.getAX() - section.getAX()) * (section.getBY() - section.getAY());
            double z2 = (section.getBX() - section.getAX()) * (temp.getBY() - section.getAY()) - (temp.getBX() - section.getAX()) * (section.getBY() - section.getAY());
            if(Math.signum(z1) != Math.signum(z2)){
                double q1 = temp.getAX() + (temp.getBX() - temp.getAX()) * Math.abs(z1) / Math.abs(z2 - z1);
                double q2 = temp.getAY() + (temp.getBY() - temp.getAY()) * Math.abs(z1) / Math.abs(z2 - z1);
                double lA = Math.sqrt((section.getAX() - q1) * (section.getAX() - q1) + (section.getAY() - q2) * (section.getAY() - q2));
                double lB = Math.sqrt((section.getBX() - q1) * (section.getBX() - q1) + (section.getBY() - q2) * (section.getBY() - q2));
                if(lA > lB) {
                    //if( rad == 400) System.out.println(q1 + "   " + q2);
                    return new WallPoint(lA, walls.get(i).getColor());
                }
            }
        }
        return new WallPoint(1000, 0);
    }

    public int isWall(double x, double y){
        double v1 = (x - 5) * (x - 5) + (y - 5) * (y - 5);
        double v2 = (x - 5) * (x - 5) + y;
        double v3 = Math.abs(x-3) + Math.abs(y*2-6);
        double v4 = Math.sin(x*2-2) + Math.sin(y*2-2);
        double v5 = Math.abs(x*2) + Math.abs(x-1) + (2* Math.sin(y*x) + Math.abs(y-4))/(Math.sqrt(3));
        if(v1 > 500) return 1;
        //if(v2 < 3) return 2;
        //if(v3 < 1) return 3;
        return 0;
    }

    public ArrayList<Entity> getEntityStaticList(){return this.entityStaticList;}

}
