package com.company.view.game;

import com.company.view.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Line;

public class GameController extends Controller {

    @FXML
    Line line;

    @FXML
    CheckBox btn;

    @FXML
    private void clickBtn(ActionEvent event){
        System.out.println("gui.Controller.clickStartBtn()");
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
        double [][]d;
        while (true){
            for(int i = 0; i < 800; i++) {
                d = controller.getAdapter().getWindow();
                controller.line.setStartX(d[i][0]);
                controller.line.setStartY(d[i][1]);
                controller.line.setEndX(d[i][0]);
                controller.line.setEndY(d[i][3]);
            }
        }
    }
}