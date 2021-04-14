package com.company.view.game;

import com.company.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;

public class GameController extends Controller {

    @FXML
    Pane pane;

    private Line []lineSky;

    private Line []lineWall;

    private Line []lineLand;

    @FXML
    Button btn;

    @FXML
    public void initialize(){
        System.out.println("gui.Controller.initialize()");
        final int count = 800;
        lineSky = new Line[count];
        lineWall = new Line[count];
        lineLand = new Line[count];
        for(int i = 0; i < count; i++){
            lineSky[i] = new Line();
            lineWall[i] = new Line();
            lineLand[i] = new Line();
            lineSky[i].setStroke(Color.CYAN);
            lineLand[i].setStroke(Color.GREY);
            lineSky[i].setStrokeWidth(0.01);
            lineWall[i].setStrokeWidth(0.01);
            lineLand[i].setStrokeWidth(0.01);

            lineSky[i].setStrokeWidth(1);
            lineSky[i].setStartX(1);
            lineSky[i].setStartY(1);
            lineSky[i].setEndX(1);
            lineSky[i].setEndY(1);

            lineWall[i].setStrokeWidth(1);
            lineWall[i].setStartX(1);
            lineWall[i].setStartY(1);
            lineWall[i].setEndX(1);
            lineWall[i].setEndY(1);

            lineLand[i].setStrokeWidth(1);
            lineLand[i].setStartX(1);
            lineLand[i].setStartY(1);
            lineLand[i].setEndX(1);
            lineLand[i].setEndY(1);
            pane.getChildren().add(lineSky[i]);
            pane.getChildren().add(lineWall[i]);
            pane.getChildren().add(lineLand[i]);
        }
        new Start("Game", this);
        new Rendering("Rendering", this);
    }

    @FXML
    void BtnOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            System.out.println("W");
            getAdapter().pressW();
        }
        if(event.getCode() == KeyCode.S){
            System.out.println("S");
            getAdapter().pressS();
        }
        if(event.getCode() == KeyCode.D){
            System.out.println("D");
            getAdapter().pressRight();
        }
        if(event.getCode() == KeyCode.A){
            System.out.println("A");
            getAdapter().pressLeft();
        }
    }

    public Line[] getLineSky(){
        return lineSky;
    }

    public Line[] getLineWall(){
        return lineWall;
    }

    public Line[] getLineLand(){
        return lineLand;
    }
}

class Start implements Runnable{

    private Thread t;
    private Controller controller;

    public Start(String nameThread, Controller controller){
        t = new Thread(this, nameThread);
        this.controller = controller;
        t.start();
    }

    public void run(){
        System.out.println("Start thread: " + t.getName());
        controller.getAdapter().run();
    }
}

class Rendering implements Runnable{

    private Thread t;
    private GameController controller;

    public Rendering(String nameThread, GameController controller){
        t = new Thread(this, nameThread);
        this.controller = controller;
        t.start();
    }

    public void run(){
        System.out.println("Start thread: " + t.getName());
        double [][]screen;

        while (true){
            screen = controller.getAdapter().getWindow();
            for (int i = 0; i < 800; i++){
                controller.getLineSky()[i].setStartX((int)screen[i][0]);
                controller.getLineSky()[i].setStartY(0);
                controller.getLineSky()[i].setEndX((int)screen[i][0]);
                controller.getLineSky()[i].setEndY((int)screen[i][1]);

                controller.getLineWall()[i].setStroke(Palette.getColor((int)screen[i][4]));
                controller.getLineWall()[i].setStartX((int)screen[i][0]);
                controller.getLineWall()[i].setStartY((int)screen[i][1]);
                controller.getLineWall()[i].setEndX((int)screen[i][0]);
                controller.getLineWall()[i].setEndY((int)screen[i][2]);

                controller.getLineLand()[i].setStartX((int)screen[i][0]);
                controller.getLineLand()[i].setStartY((int)screen[i][2]);
                controller.getLineLand()[i].setEndX((int)screen[i][0]);
                controller.getLineLand()[i].setEndY((int)screen[i][3]);
            }
        }

        //controller.getLine()[0].setEndX(800);
    }
}

class Palette{
    public static Color getColor(int n) {
        if (n == 0) return Color.RED;
        if (n == 1) return Color.GREEN;
        if (n == 2) return Color.BLUE;
        if (n == 3) return Color.BLUEVIOLET;
        if (n == 4) return Color.CORAL;
        return Color.RED;
    }
}