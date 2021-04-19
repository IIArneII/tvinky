package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class RayCasting{

    //Констатнты - размеры
    public static final int Width = 800;
    public static final int Height  = 600;
    public static final int Width_Center = Width/2;
    public static final int Height_Center = Height/2;

    //Константы - действия
    public static final int STOP = -1;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int SHOOT = 5;

    //Атрибуты - Графика
    private Map map;

    //Атрибуты - действия
    private int eventCharacter = STOP;

    private Character charcter = new Character();
    private double[][] screen;

    //Лист с объектами
    public List<Object> listObjects = new ArrayList<Object>();

    public RayCasting(){
        this.map = new Map();

        this.screen = new double[800][5];
    }

    public void run(){
        double radX, radY, stepX, stepY, angRad;

        while(true){
            double [][]temp = new double[800][6];

            angRad = (charcter.getAngCharacter() -  Angles.Ang30);

            for(int rad = 0; rad<Angles.Ang60; rad++){
                radX = charcter.getPosCharacterX();
                radY = charcter.getPosCharacterY();
                stepX = Math.cos(Angles.converteDegreeToRadian(angRad)) / 80;
                stepY = Math.sin(Angles.converteDegreeToRadian(angRad)) / 80;

                int wall = 0;
                int distant = 1;
                while (wall == 0){
                    radX += stepX;
                    radY += stepY;
                    wall = this.map.getMap()[(int)radX][(int)radY];
                    distant++;
                }

                int heightWall = (30000/distant);

                temp[rad][0] = rad;
                temp[rad][1] = Height_Center - heightWall;
                temp[rad][2] = Height_Center + heightWall;
                temp[rad][3] = Height;
                temp[rad][4] = wall-1;
                temp[rad][5] = 0;

                if(this.eventCharacter == SHOOT){
                    temp[rad][5] = 1;
                }

                angRad++;
            }
            this.screen = temp;

            for (int i = 0; i< listObjects.size(); i++){
                if(listObjects.get(i).getType() == "bullet"){
                    listObjects.get(i).setX(listObjects.get(i).getX()+listObjects.get(i).getVx()*0.1);
                    listObjects.get(i).setY(listObjects.get(i).getY()+listObjects.get(i).getVy()*0.1);
                    if(this.map.getMap()[(int) listObjects.get(i).getX()][(int) listObjects.get(i).getY()] > 0){
                        listObjects.get(i).setRemove(true);
                        listObjects.remove(i);
                    }
                }
            }

            double posCharacterXPrev = charcter.getPosCharacterX();
            double posCharacterYPrev = charcter.getPosCharacterY();
            switch (this.eventCharacter){
                case UP:
                    this.caseUp(posCharacterXPrev, posCharacterYPrev);
                    break;

                case DOWN:
                    this.caseDown(posCharacterXPrev, posCharacterYPrev);
                    break;

                case LEFT:
                    this.caseLeft();
                    break;

                case RIGHT:
                    this.caseRight();
                    break;
                case SHOOT:
                    this.caseShoot();
                    break;
            }
        }

    }

    public void caseUp(double posCharacterXPrev, double posCharacterYPrev){
        posCharacterXPrev = charcter.getPosCharacterX();
        posCharacterYPrev = charcter.getPosCharacterY();
        charcter.setPosCharacterXPlus(Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        charcter.setPosCharacterYPlus(Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
            charcter.setPosCharacterXRedefinition(posCharacterXPrev);
            charcter.setPosCharacterYRedefinition(posCharacterYPrev);
        }
    }

    public void caseDown(double posCharacterXPrev, double posCharacterYPrev){
        posCharacterXPrev = charcter.getPosCharacterX();
        posCharacterYPrev = charcter.getPosCharacterY();
        charcter.setPosCharacterXMinus(Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        charcter.setPosCharacterYMinus(Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
            charcter.setPosCharacterXRedefinition(posCharacterXPrev);
            charcter.setPosCharacterYRedefinition(posCharacterYPrev);
        }
    }

    public void caseLeft(){
        charcter.setAngCharacterMinus(0.1 * (Angles.Ang6/16));
    }

    public void caseRight(){
        charcter.setAngCharacterPlus(0.1 * (Angles.Ang6/16));
    }

    public void caseShoot(){
        Object o = new Object(charcter.getPosCharacterX(), charcter.getPosCharacterY(), 1, "bullet");
        o.setVx(Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) * 6);
        o.setVy(Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) * 6);
        this.listObjects.add(o);
    }

    public int getEventCharacter(){return this.eventCharacter;}

    public void setEventCharacter(int n){
        this.eventCharacter = n;
    }

    public double[][] getScreen(){return this.screen;}
}