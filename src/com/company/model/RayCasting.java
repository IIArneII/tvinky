package com.company.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class RayCasting extends JFrame implements Runnable, KeyListener{

    //Констатнты - размеры
    public static final int Width = 800;
    public static final int Height  = 600;
    public static final int Width_Center = Width/2;
    public static final int Height_Center = Height/2;

    //Константы - углы
    public static final int Ang360 = 4800;
    public static final int Ang180 = 2400;
    public static final int Ang60 = 800;
    public static final int Ang30 = 400;
    public static final int Ang6 = 80;

    //Константы - действия
    public static final int STOP = -1;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    //Атрибуты - Графика
    private BufferedImage image;
    private Graphics graphics;
    private Color[] clr_palette;
    private int[][] map;

    //Атрибуты - действия
    private int eventCharacter = STOP;

    public RayCasting(){
        this.image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        this.graphics = this.image.getGraphics();

        this.clr_palette = new Color[]{Color.GREEN, Color.red, Color.blue, Color.orange, Color.WHITE, Color.YELLOW};
        this.map = new int[][]{ {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {5, 0, 0, 0, 0, 4, 2, 3, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 3, 3, 3, 0, 4, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        this.setTitle("Ray_Casting");
        this.setSize(Width, Height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setVisible(true);

        new Thread(this).start();
    }

    public double converte_DegreeToRadian(int angle){
        return angle * (Math.PI / Ang180);
    }

    @Override
    public void run(){
        double posCharacterX = 5, posCharacterY = 5, radX, radY, stepX, stepY;
        int angCharacter = 0, angRad;

        while(true){


            angRad = (angCharacter -  Ang30);

            for(int rad = 0; rad<Ang60; rad++){
                radX = posCharacterX;
                radY = posCharacterY;
                stepX = Math.cos(this.converte_DegreeToRadian(angRad)) / 80;
                stepY = Math.sin(this.converte_DegreeToRadian(angRad)) / 80;

                int wall = 0;
                int distant = 1;
                while (wall == 0){
                    radX += stepX;
                    radY += stepY;
                    wall = this.map[(int)radX][(int)radY];
                    distant++;
                }

                int heightWall = (2000/distant);

                //Sky
                this.graphics.setColor(Color.CYAN);
                this.graphics.drawLine(rad, 0, rad, Height_Center - heightWall);

                //Wall
                this.graphics.setColor(this.clr_palette[wall - 1]);
                this.graphics.drawLine(rad, Height_Center - heightWall, rad, Height_Center + heightWall);

                //Floor
                this.graphics.setColor(Color.GRAY);
                this.graphics.drawLine(rad, Height_Center + heightWall, rad, Height);

                angRad++;
            }

            this.repaint();

            double posCharacterXPrev;
            double posCharacterYPrev;
            switch (this.eventCharacter){
                case UP:
                    posCharacterXPrev = posCharacterX;
                    posCharacterYPrev = posCharacterY;
                    posCharacterX += Math.cos(this.converte_DegreeToRadian(angCharacter)) /1000;
                    posCharacterY += Math.sin(this.converte_DegreeToRadian(angCharacter)) /1000;
                    if(this.map[(int) posCharacterX][(int) posCharacterY] > 0){
                        posCharacterX = posCharacterXPrev;
                        posCharacterY = posCharacterYPrev;
                    }
                    break;

                case DOWN:
                    posCharacterXPrev = posCharacterX;
                    posCharacterYPrev = posCharacterY;
                    posCharacterX -= Math.cos(this.converte_DegreeToRadian(angCharacter)) /1000;
                    posCharacterY -= Math.sin(this.converte_DegreeToRadian(angCharacter)) /1000;
                    if(this.map[(int) posCharacterX][(int) posCharacterY] > 0){
                        posCharacterX = posCharacterXPrev;
                        posCharacterY = posCharacterYPrev;
                    }
                    break;

                case LEFT:
                    angCharacter -= 0.1 * (Ang6/6);
                    break;

                case RIGHT:
                    angCharacter += 0.1 * (Ang6/6);
                    break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: this.eventCharacter = UP; break;
            case KeyEvent.VK_DOWN: this.eventCharacter = DOWN; break;
            case KeyEvent.VK_LEFT: this.eventCharacter = LEFT; break;
            case KeyEvent.VK_RIGHT: this.eventCharacter = RIGHT; break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: this.eventCharacter = STOP; break;
            case KeyEvent.VK_DOWN: this.eventCharacter = STOP; break;
            case KeyEvent.VK_LEFT: this.eventCharacter = STOP; break;
            case KeyEvent.VK_RIGHT: this.eventCharacter = STOP; break;
        }


    }

    @Override
    public void paint(Graphics g){
        g.drawImage(this.image, 0, 0, this);

    }

    public static void main(String[] args){
        new RayCasting();

    }
}