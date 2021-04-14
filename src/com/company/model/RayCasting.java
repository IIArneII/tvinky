package com.company.model;

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

    //Атрибуты - Графика
    private Map map;

    //Атрибуты - действия
    private int eventCharacter = STOP;

    private Character charcter = new Character();
    private double[][] screen;

    public RayCasting(){
        this.map = new Map();

        this.screen = new double[800][5];
    }

    public void run(){
        double radX, radY, stepX, stepY, angRad;

        while(true){

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

                this.screen[rad][0] = rad;
                this.screen[rad][1] = Height_Center - heightWall;
                this.screen[rad][2] = Height_Center + heightWall;
                this.screen[rad][3] = Height;
                this.screen[rad][4] = wall-1;

                angRad++;
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
            }
        }

    }

    public void caseUp(double posCharacterXPrev, double posCharacterYPrev){
        posCharacterXPrev = charcter.getPosCharacterX();
        posCharacterYPrev = charcter.getPosCharacterY();
        charcter.setPosCharacterXPlus(Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /100);
        charcter.setPosCharacterYPlus(Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /100);
        if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
            charcter.setPosCharacterXRedefinition(posCharacterXPrev);
            charcter.setPosCharacterYRedefinition(posCharacterYPrev);
        }
    }

    public void caseDown(double posCharacterXPrev, double posCharacterYPrev){
        posCharacterXPrev = charcter.getPosCharacterX();
        posCharacterYPrev = charcter.getPosCharacterY();
        charcter.setPosCharacterXMinus(Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /100);
        charcter.setPosCharacterYMinus(Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /100);
        if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
            charcter.setPosCharacterXRedefinition(posCharacterXPrev);
            charcter.setPosCharacterYRedefinition(posCharacterYPrev);
        }
    }

    public void caseLeft(){
        charcter.setAngCharacterMinus(0.1 * (Angles.Ang6/6));
    }

    public void caseRight(){
        charcter.setAngCharacterPlus(0.1 * (Angles.Ang6/6));
    }

    public int getEventCharacter(){return this.eventCharacter;}

    public void setEventCharacter(int n){
        this.eventCharacter = n;
    }

    public double[][] getScreen(){return this.screen;}
}