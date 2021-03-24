package com.company.gui;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    private String userNameFieldText;

    @FXML
    private Button startBtn;

    @FXML
    private Button startOnlineBtn;

    @FXML
    private Button optionsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField userNameField;

    @FXML
    private void clickUserNameField(ActionEvent event) {
        System.out.println("gui.Controller.clickUserNameField()");
        userNameFieldText = userNameField.getText();
    }

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
    private void clickOptionsBtn(ActionEvent event) throws Exception{
        System.out.println("gui.Controller.clickOptionsBtn()");
        Stage theStage = (Stage)exitBtn.getScene().getWindow();
        Parent theRoot = FXMLLoader.load(getClass().getResource("OptionView.fxml"));
        theStage.setScene(new Scene(theRoot));
    }

    @FXML
    private void clickExitBtn(ActionEvent event){
        System.out.println("gui.Controller.clickExitBtn()");
        Stage theStage = (Stage)exitBtn.getScene().getWindow();
        theStage.close();
    }
}
