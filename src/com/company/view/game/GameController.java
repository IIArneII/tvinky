package com.company.view.game;

import com.company.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class GameController extends Controller {

    @FXML
    GridPane pane;

    private Line []line;

    @FXML
    public void initialize(){
        System.out.println("gui.Controller.initialize()");
        final int count = 800;
        line = new Line[count];
        for(int i = 0; i < count; i++){
            line[i] = new Line();
            line[i].setStartX(1);
            line[i].setStartY(1);
            line[i].setEndX(i);
            line[i].setEndY(200);
            pane.add(line[i], 0, 0);
        }
        new Rendering("Rendering", this);
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
        
    }
}