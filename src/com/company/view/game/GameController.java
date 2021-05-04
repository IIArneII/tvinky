package com.company.view.game;

import com.company.model.RenderingAdapter;
import com.company.model.Screen;
import com.company.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class GameController extends Controller {

    RenderingAdapter renderingAdapter;

    int e = 0;

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
            lineSky[i].setStrokeWidth(1);
            lineWall[i].setStrokeWidth(1);
            lineLand[i].setStrokeWidth(1);

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
        renderingAdapter = new RenderingAdapter(this);
        this.getAdapter().setRenderingAdapter(renderingAdapter);
        new Start("Game", this);
        new Rendering("Rendering", this);
    }

    @FXML
    public void drawLines(Screen screen){
        Screen temp = screen.copyScreen();
        for (int i = 0; i < 800; i++){
            this.getLineSky()[i].setStartX(temp.getScreen()[i][0]);
            this.getLineSky()[i].setStartY(0);
            this.getLineSky()[i].setEndX(temp.getScreen()[i][0]);
            this.getLineSky()[i].setEndY(temp.getScreen()[i][1]);

            this.getLineWall()[i].setStroke(Palette.getColor(temp.getScreen()[i][4]));
            this.getLineWall()[i].setStartX(temp.getScreen()[i][0]);
            this.getLineWall()[i].setStartY(temp.getScreen()[i][1]);
            this.getLineWall()[i].setEndX(temp.getScreen()[i][0]);
            this.getLineWall()[i].setEndY(temp.getScreen()[i][2]);

            this.getLineLand()[i].setStartX(temp.getScreen()[i][0]);
            this.getLineLand()[i].setStartY(temp.getScreen()[i][2]);
            this.getLineLand()[i].setEndX(temp.getScreen()[i][0]);
            this.getLineLand()[i].setEndY(temp.getScreen()[i][3]);
        }
    }

    @FXML
    public void drawLine(int rad, int r1, int r2, int r3, int r4){
        this.getLineSky()[rad].setStartX(rad);
        this.getLineSky()[rad].setStartY(0);
        this.getLineSky()[rad].setEndX(rad);
        this.getLineSky()[rad].setEndY(r1);

        this.getLineWall()[rad].setStroke(Palette.getColor(r4));
        this.getLineWall()[rad].setStartX(rad);
        this.getLineWall()[rad].setStartY(r1);
        this.getLineWall()[rad].setEndX(rad);
        this.getLineWall()[rad].setEndY(r2);

        this.getLineLand()[rad].setStartX(rad);
        this.getLineLand()[rad].setStartY(r2);
        this.getLineLand()[rad].setEndX(rad);
        this.getLineLand()[rad].setEndY(r3);
    }

    @FXML
    void btnOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            getAdapter().pressW();
        }
        if(event.getCode() == KeyCode.S){
            getAdapter().pressS();
        }

        if(event.getCode() == KeyCode.A){
            getAdapter().pressA();
        }
        if(event.getCode() == KeyCode.D){
            getAdapter().pressD();
        }

        if(event.getCode() == KeyCode.RIGHT){
            getAdapter().pressRight();
        }
        if(event.getCode() == KeyCode.LEFT){
            getAdapter().pressLeft();
        }
        if(event.getCode() == KeyCode.SPACE){
            getAdapter().pressShot();
        }
    }

    @FXML
    void btnOnKeyReleased(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            getAdapter().pressWReleased();
        }
        if(event.getCode() == KeyCode.S){
            getAdapter().pressSReleased();
        }

        if(event.getCode() == KeyCode.A){
            getAdapter().pressAReleased();
        }
        if(event.getCode() == KeyCode.D){
            getAdapter().pressDReleased();
        }

        if(event.getCode() == KeyCode.RIGHT){
            getAdapter().pressRightReleased();
        }
        if(event.getCode() == KeyCode.LEFT){
            getAdapter().pressLeftReleased();
        }
        if(event.getCode() == KeyCode.SPACE){
            getAdapter().pressShotReleased();
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
        controller.getAdapter().start();
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

        while (true){
            try { t.sleep(30); } catch (Exception e){}
            final Screen screen = controller.getAdapter().getScreen();
            Platform.runLater(new Runnable() {
                public void run() {
                    controller.drawLines(screen);
                }
            });
            /*
            for (int i = 0; i < 800; i++){
                //System.out.println(screen.getScreen()[i][2]);
                controller.getLineSky()[i].setStartX(screen.getScreen()[i][0]);
                controller.getLineSky()[i].setStartY(0);
                controller.getLineSky()[i].setEndX(screen.getScreen()[i][0]);
                controller.getLineSky()[i].setEndY(screen.getScreen()[i][1]);

                controller.getLineWall()[i].setStroke(Palette.getColor(screen.getScreen()[i][4]));
                controller.getLineWall()[i].setStartX(screen.getScreen()[i][0]);
                controller.getLineWall()[i].setStartY(screen.getScreen()[i][1]);
                controller.getLineWall()[i].setEndX(screen.getScreen()[i][0]);
                controller.getLineWall()[i].setEndY(screen.getScreen()[i][2]);

                controller.getLineLand()[i].setStartX(screen.getScreen()[i][0]);
                controller.getLineLand()[i].setStartY(screen.getScreen()[i][2]);
                controller.getLineLand()[i].setEndX(screen.getScreen()[i][0]);
                controller.getLineLand()[i].setEndY(screen.getScreen()[i][3]);
            }
             */
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