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
    private Map map;

    private Character charcter = new Character();

    //Атрибуты - действия
    private int eventCharacter = STOP;

    public RayCasting(){
        this.image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        this.graphics = this.image.getGraphics();

        this.clr_palette = new Color[]{Color.GREEN, Color.red, Color.blue, Color.orange, Color.WHITE, Color.YELLOW};
        this.map = new Map();

        this.setTitle("Ray_Casting");
        this.setSize(Width, Height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setVisible(true);

        new Thread(this).start();
    }

    @Override
    public void run(){
        double radX, radY, stepX, stepY, angRad;

        while(true){

            System.out.println(Thread.currentThread().getName());
            angRad = (charcter.getAngCharacter() -  Angles.Ang30);

            for(int rad = 0; rad<Angles.Ang60; rad++){
                radX = charcter.getPosCharacterX();
                radY = charcter.getPosCharacterY();
                stepX = Math.cos(Angles.converte_DegreeToRadian(angRad)) / 80;
                stepY = Math.sin(Angles.converte_DegreeToRadian(angRad)) / 80;

                int wall = 0;
                int distant = 1;
                while (wall == 0){
                    radX += stepX;
                    radY += stepY;
                    wall = this.map.getMap()[(int)radX][(int)radY];
                    distant++;
                }

                int heightWall = (20000/distant);

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
                    posCharacterXPrev = charcter.getPosCharacterX();
                    posCharacterYPrev = charcter.getPosCharacterY();
                    charcter.PosCharacterXPlus(Math.cos(Angles.converte_DegreeToRadian(charcter.getAngCharacter())) /100);
                    charcter.PosCharacterYPlus(Math.sin(Angles.converte_DegreeToRadian(charcter.getAngCharacter())) /100);
                    if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
                        charcter.PosCharacterXRedefinition(posCharacterXPrev);
                        charcter.PosCharacterYRedefinition(posCharacterYPrev);
                    }
                    break;

                case DOWN:
                    posCharacterXPrev = charcter.getPosCharacterX();
                    posCharacterYPrev = charcter.getPosCharacterY();
                    charcter.PosCharacterXMinus(Math.cos(Angles.converte_DegreeToRadian(charcter.getAngCharacter())) /100);
                    charcter.PosCharacterYMinus(Math.sin(Angles.converte_DegreeToRadian(charcter.getAngCharacter())) /100);
                    if(this.map.getMap()[(int) charcter.getPosCharacterX()][(int) charcter.getPosCharacterY()] > 0){
                        charcter.PosCharacterXRedefinition(posCharacterXPrev);
                        charcter.PosCharacterYRedefinition(posCharacterYPrev);
                    }
                    break;

                case LEFT:
                    charcter.AngCharacterMinus(0.1 * (Angles.Ang6/6));
                    break;

                case RIGHT:
                    charcter.AngCharacterPlus(0.1 * (Angles.Ang6/6));
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