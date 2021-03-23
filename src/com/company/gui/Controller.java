package com.company.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button startBtn;

    @FXML
    private Button startOnlineBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField userNameField;

    @FXML
    private void clickStartBtn(ActionEvent event) {
        System.out.println("gui.Controller.clickStartBtn()");
    }

    @FXML
    private void clickStartOnlineBtn(ActionEvent event) {
        System.out.println("gui.Controller.clickStartOnlineBtn()");
        if(userNameField.getText() != "") {

        }
        else {
            userNameField.setStyle("-fx-background-color: #FAC1BF");
        }
    }

    @FXML
    private void clickExitBtn(ActionEvent event){
        System.out.println("gui.Controller.clickExitBtn()");
        Stage theStage = (Stage)exitBtn.getScene().getWindow();
        theStage.close();
    }
}
