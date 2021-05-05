package com.company.view.game;

import com.company.model.Adapter;
import com.company.model.Screen;
import com.company.view.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class GameController{

    Adapter adapter;

    private boolean renderingLaunched;

    private boolean renderingOnPause;

    @FXML Button menuExitBtn;

    @FXML Button menuContinueBtn;

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
        renderingLaunched = false;
        renderingOnPause = false;
        adapter = new Adapter();
        adapter.setRenderingMethod(Info.renderingMethod);
        new Start("Game", this);
        new Rendering("Rendering", this);
    }

    public Adapter getAdapter(){
        return adapter;
    }

    public boolean isRenderingOnPause() {
        return renderingOnPause;
    }

    public boolean isRenderingLaunched() {
        return renderingLaunched;
    }

    public void setRenderingOnPause(boolean renderingOnPause) {
        this.renderingOnPause = renderingOnPause;
    }

    public void setRenderingLaunched(boolean renderingLaunched) {
        this.renderingLaunched = renderingLaunched;
    }

    @FXML
    private void clickMenuContinueBtn(ActionEvent event){
        adapter.pause(false);
        pane.setVisible(true);
        menuContinueBtn.setVisible(false);
        menuExitBtn.setVisible(false);
    }

    @FXML
    private void clickMenuExitBtn(ActionEvent event) throws Exception{
        adapter.stop();
        renderingLaunched = false;
        Scene theScene = menuExitBtn.getScene();
        Parent theRoot = FXMLLoader.load(getClass().getResource("../main/MainView.fxml"));
        theScene.setRoot(theRoot);
    }

    @FXML
    public void drawLines(Screen screen) throws Exception{
        Scene theScene = menuExitBtn.getScene();
        double x = (theScene.getWidth() / 800);
        double y = (theScene.getHeight() / 600);
        Screen temp = screen.copyScreen();
        for (int i = 0; i < 800; i++){
            this.getLineSky()[i].setStartX((int)(x * temp.getScreen()[i][0]));
            this.getLineSky()[i].setStartY(0);
            this.getLineSky()[i].setEndX((int)(x * temp.getScreen()[i][0]));
            this.getLineSky()[i].setEndY((int)(y * temp.getScreen()[i][1]));

            this.getLineWall()[i].setStroke(Palette.getColor(temp.getScreen()[i][4]));
            this.getLineWall()[i].setStartX((int)(x * temp.getScreen()[i][0]));
            this.getLineWall()[i].setStartY((int)(y * temp.getScreen()[i][1]));
            this.getLineWall()[i].setEndX((int)(x * temp.getScreen()[i][0]));
            this.getLineWall()[i].setEndY((int)(y * temp.getScreen()[i][2]));

            this.getLineLand()[i].setStartX((int)(x * temp.getScreen()[i][0]));
            this.getLineLand()[i].setStartY((int)(y * temp.getScreen()[i][2]));
            this.getLineLand()[i].setEndX((int)(x * temp.getScreen()[i][0]));
            this.getLineLand()[i].setEndY((int)(y * temp.getScreen()[i][3]));
        }
    }

    @FXML
    public void resetLines(){
        for(int i = 0; i < 800; i++) {
            lineSky[i].setStartX(1);
            lineSky[i].setStartY(1);
            lineSky[i].setEndX(1);
            lineSky[i].setEndY(1);

            lineWall[i].setStartX(1);
            lineWall[i].setStartY(1);
            lineWall[i].setEndX(1);
            lineWall[i].setEndY(1);

            lineLand[i].setStartX(1);
            lineLand[i].setStartY(1);
            lineLand[i].setEndX(1);
            lineLand[i].setEndY(1);
        }
    }

    @FXML
    void btnOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            adapter.pressW();
        }
        if(event.getCode() == KeyCode.S){
            adapter.pressS();
        }

        if(event.getCode() == KeyCode.A){
            adapter.pressA();
        }
        if(event.getCode() == KeyCode.D){
            adapter.pressD();
        }

        if(event.getCode() == KeyCode.RIGHT){
            adapter.pressRight();
        }
        if(event.getCode() == KeyCode.LEFT){
            adapter.pressLeft();
        }
        if(event.getCode() == KeyCode.SPACE){
            adapter.pressShot();
        }
        if(event.getCode() == KeyCode.ESCAPE){
            renderingOnPause = true;
            adapter.pause(true);
            pane.setVisible(false);
            menuContinueBtn.setVisible(true);
            menuExitBtn.setVisible(true);
        }
    }

    @FXML
    void btnOnKeyReleased(KeyEvent event){
        if(event.getCode() == KeyCode.W){
            adapter.pressWReleased();
        }
        if(event.getCode() == KeyCode.S){
            adapter.pressSReleased();
        }

        if(event.getCode() == KeyCode.A){
            adapter.pressAReleased();
        }
        if(event.getCode() == KeyCode.D){
            adapter.pressDReleased();
        }

        if(event.getCode() == KeyCode.RIGHT){
            adapter.pressRightReleased();
        }
        if(event.getCode() == KeyCode.LEFT){
            adapter.pressLeftReleased();
        }
        if(event.getCode() == KeyCode.SPACE){
            adapter.pressShotReleased();
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
    private GameController controller;

    public Start(String nameThread, GameController controller){
        t = new Thread(this, nameThread);
        this.controller = controller;
        t.start();
        System.out.println("Поток запуска завершился");
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
        controller.setRenderingLaunched(true);
        while (controller.isRenderingLaunched()){
            if(!controller.isRenderingOnPause()){
                try { t.sleep(1); } catch (Exception e){}
                final Screen screen = controller.getAdapter().getScreen();
                Platform.runLater(new Runnable() {
                    public void run() {
                        try { controller.drawLines(screen); } catch (Exception e){}
                    }
                });
            }
            else try { t.sleep(10); } catch (Exception e){}
        }
        System.out.println("Поток отрисовки котроллера завершился");
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