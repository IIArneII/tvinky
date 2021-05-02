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
    public static final int SHOOT = 5;

    //Атрибуты - Графика
    private Map map;

    //Атрибуты - действия
    private int eventCharacter = STOP;

    private Character charcter = new Character();
    private double[][] screen;

    //Лист с объектами


    public RayCasting(){
        this.map = new Map();

        this.screen = new double[800][5];
    }

    public void run(){
        double radX, radY, stepX, stepY, angRad;

        while(true){
            double [][]temp = new double[800][5];

            angRad = (charcter.getAngCharacter() -  Angles.Ang30);

            for(int rad = 0; rad<Angles.Ang60; rad++){
                radX = charcter.getX();
                radY = charcter.getY();
                stepX = Math.cos(Angles.converteDegreeToRadian(angRad)) / 80;
                stepY = Math.sin(Angles.converteDegreeToRadian(angRad)) / 80;

                int wall = 0;
                int distant = 1;
                while (wall == 0)
                {
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

                angRad++;
            }
            this.screen = temp;


            double posCharacterXPrev = charcter.getX();
            double posCharacterYPrev = charcter.getY();
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
        posCharacterXPrev = charcter.getX();
        posCharacterYPrev = charcter.getY();
        charcter.setX(charcter.getX() + Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        charcter.setY(charcter.getY() + Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        if(this.map.getMap()[(int) charcter.getX()][(int) charcter.getY()] > 0){
            charcter.setX(posCharacterXPrev);
            charcter.setY(posCharacterYPrev);
        }
    }

    public void caseDown(double posCharacterXPrev, double posCharacterYPrev){
        posCharacterXPrev = charcter.getX();
        posCharacterYPrev = charcter.getY();
        charcter.setX(charcter.getX() - Math.cos(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        charcter.setY(charcter.getY() - Math.sin(Angles.converteDegreeToRadian(charcter.getAngCharacter())) /500);
        if(this.map.getMap()[(int) charcter.getX()][(int) charcter.getY()] > 0){
            charcter.setX(posCharacterXPrev);
            charcter.setY(posCharacterYPrev);
        }
    }

    public void caseLeft(){
        charcter.setAngCharacter(charcter.getAngCharacter() - 0.1 * (Angles.Ang6/16));
    }

    public void caseRight(){
        charcter.setAngCharacter(charcter.getAngCharacter() + 0.1 * (Angles.Ang6/16));
    }

    public void caseShoot(){

        double distant = 0;
        boolean hitWall = false;

        double angRad = (charcter.getAngCharacter() -  Angles.Ang30 / 6);

        double stepX = Math.cos(Angles.converteDegreeToRadian(angRad)) / 80;
        double stepY = Math.sin(Angles.converteDegreeToRadian(angRad)) / 80;

        while (!hitWall){
            distant += 1;

            double radX = charcter.getX() + stepX *distant;
            double radY = charcter.getY() + stepY *distant;

            if(map.getMap()[(int)radX][(int)radY] != 0){
                hitWall = true;
                System.out.print("Hitwall. coordinates:" + (int)radX + " " + (int)radY);
                this.map.setMap((int)radX, (int)radY, 0);
            }
            /*
            else{

            }
            */
        }
    }

    public int getEventCharacter(){return this.eventCharacter;}

    public void setEventCharacter(int n){
        this.eventCharacter = n;
    }

    public double[][] getScreen(){return this.screen;}
}