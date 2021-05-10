package com.company.view.game;

import java.io.File;
import com.company.model.Adapter;
import com.company.model.rendering.Screen;
import com.company.view.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import javafx.scene.image.WritableImage;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameController{

    Adapter adapter;

    private boolean renderingLaunched;

    private boolean renderingOnPause;

    @FXML Button menuExitBtn;

    @FXML Button menuContinueBtn;

    @FXML
    Pane pane;

    private Line []lineSky;

    //private Line []lineWall;

    private ImageView [] textureWall;

    private ArrayList<Image[]> textures;

    private Line []lineLand;

    @FXML
    Button btn;

    @FXML
    public void initialize(){
        System.out.println("gui.Controller.initialize()");
        try {
            textures = new ArrayList<>();
            File[] files = new File(".\\textures").listFiles();
            for(int i = 0; i < files.length; i++){
                System.out.println(files[i].getAbsolutePath());
                Image temp = new Image(new FileInputStream(files[i]));
                Image[] image = new Image[(int)temp.getWidth()];
                //System.out.println(temp.getWidth());
                for(int j = 0; j < temp.getWidth(); j++){
                    //System.out.println(j);
                    image[j] = new WritableImage(temp.getPixelReader(), j, 0, 1, (int)temp.getHeight());
                }
                textures.add(image);
            }
        }
        catch (Exception e){}

        final int count = 800;
        lineSky = new Line[count];
        textureWall = new ImageView[count];
        //lineWall = new Line[count];
        lineLand = new Line[count];
        for(int i = 0; i < count; i++){
            lineSky[i] = new Line();
            //lineWall[i] = new Line();
            textureWall[i] = new ImageView();
            lineLand[i] = new Line();
            lineSky[i].setStroke(Color.CYAN);
            lineLand[i].setStroke(Color.GREY);
            lineSky[i].setStrokeWidth(1);
            //lineWall[i].setStrokeWidth(1);
            lineLand[i].setStrokeWidth(1);

            lineSky[i].setStrokeWidth(1);
            lineSky[i].setStartX(1);
            lineSky[i].setStartY(1);
            lineSky[i].setEndX(1);
            lineSky[i].setEndY(1);

            //lineWall[i].setStrokeWidth(1);
            //lineWall[i].setStartX(1);
            //lineWall[i].setStartY(1);
            //lineWall[i].setEndX(1);
            //lineWall[i].setEndY(1);

            lineLand[i].setStrokeWidth(1);
            lineLand[i].setStartX(1);
            lineLand[i].setStartY(1);
            lineLand[i].setEndX(1);
            lineLand[i].setEndY(1);
            pane.getChildren().add(lineSky[i]);
            //pane.getChildren().add(lineWall[i]);
            pane.getChildren().add(textureWall[i]);
            pane.getChildren().add(lineLand[i]);
        }
        renderingLaunched = false;
        renderingOnPause = false;
        adapter = new Adapter();
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
        renderingOnPause = false;
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
    public void drawLines(Screen screen){
        try {
            for (int i = 0; i < 800; i++){
                this.getLineSky()[i].setStartX(screen.getScreen()[i][0]);
                this.getLineSky()[i].setStartY(0);
                this.getLineSky()[i].setEndX(screen.getScreen()[i][0]);
                this.getLineSky()[i].setEndY(screen.getScreen()[i][1]);

                int textureID = screen.getScreen()[i][5];
                textureWall[i].setImage(textures.get(textureID)[(int)(textures.get(textureID).length * screen.getWidth()[i])]);
                textureWall[i].setX(screen.getScreen()[i][0]);
                textureWall[i].setY(screen.getScreen()[i][1]);
                textureWall[i].setFitWidth(10);
                textureWall[i].setFitHeight(screen.getScreen()[i][2] - screen.getScreen()[i][1]);

            /*
            this.getLineWall()[i].setStroke(Palette.getColor(screen.getScreen()[i][4]));
            this.getLineWall()[i].setStartX(screen.getScreen()[i][0]);
            this.getLineWall()[i].setStartY(screen.getScreen()[i][1]);
            this.getLineWall()[i].setEndX(screen.getScreen()[i][0]);
            this.getLineWall()[i].setEndY(screen.getScreen()[i][2]);*/

                this.getLineLand()[i].setStartX(screen.getScreen()[i][0]);
                this.getLineLand()[i].setStartY(screen.getScreen()[i][2]);
                this.getLineLand()[i].setEndX(screen.getScreen()[i][0]);
                this.getLineLand()[i].setEndY(screen.getScreen()[i][3]);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка");
        }
    }

    @FXML
    public void resetLines(){
        for(int i = 0; i < 800; i++) {
            lineSky[i].setStartX(1);
            lineSky[i].setStartY(1);
            lineSky[i].setEndX(1);
            lineSky[i].setEndY(1);

            //lineWall[i].setStartX(1);
            //lineWall[i].setStartY(1);
            //lineWall[i].setEndX(1);
            //lineWall[i].setEndY(1);

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
            //ObservableList<Node> list = pane.getChildren();
            //list.clear();
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

    //public Line[] getLineWall(){
        //return lineWall;
    //}

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
            else try { t.sleep(10);
                System.out.println("Птокок рендеринг в котроллере на паузе");} catch (Exception e){}
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
        if (n == 4) return Color.BISQUE;
        return Color.RED;
    }
}