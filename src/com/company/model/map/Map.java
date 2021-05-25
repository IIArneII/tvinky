package com.company.model.map;

import com.company.model.entity.Entity;

import java.util.ArrayList;

public class Map {
    //private int[][] map;
    private ArrayList<Entity> entityStaticList;
    private ArrayList<Wall> walls;

    public Map(){
        walls = new ArrayList<>();

        walls.add(new Wall(2, 0, 0, 2, 0, 3, 1));

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

    //public WallPoint distance(Section section, int rad){
        /*ArrayList<WallPoint> wallPoints = new ArrayList<>();
        for(int i = 0; i < this.walls.size(); i++){
            Section temp = walls.get(i).getSection();
            double z1 = section.crossProduct(new Section(section.getA(), temp.getA()));
            double z2 = section.crossProduct(new Section(section.getA(), temp.getB()));
            if(Math.signum(z1) != Math.signum(z2)){
                double q1 = temp.getA().getX() + (temp.getB().getX() - temp.getA().getX()) * Math.abs(z1) / Math.abs(z2 - z1);
                double q2 = temp.getA().getY() + (temp.getB().getY() - temp.getA().getY()) * Math.abs(z1) / Math.abs(z2 - z1);
                double lA = section.getA().distance(new Point(q1, q2));
                double lB = section.getB().distance(new Point(q1, q2));
                if(lA > lB) {
                    double l = new Section(temp.getA(), new Point(q1, q2)).length();
                    double k = (l / walls.get(i).getTextureWidth()) - (int)(l / walls.get(i).getTextureWidth());
                    wallPoints.add(new WallPoint(lA, walls.get(i).getColor(), walls.get(i).getTextureID(), k));
                }
            }
        }
        WallPoint min = new WallPoint(1000, 0, 0, 0);
        for(int i = 0; i < wallPoints.size(); i++){
            if(wallPoints.get(i).getDistance() < min.getDistance()) min = wallPoints.get(i);
        }
        return min;
    }*/

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

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
