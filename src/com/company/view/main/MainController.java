package com.company.view.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
    private String userNameFieldText;

    @FXML
    private Button startBtn;

    @FXML
    private Button onlineBtn;

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
    private void clickStartOnlineBtn(ActionEvent event) throws Exception{
        System.out.println("gui.Controller.clickStartOnlineBtn()");
        Scene theScene = onlineBtn.getScene();
        Parent theRoot = FXMLLoader.load(getClass().getResource("../online/OnlineView.fxml"));
        theScene.setRoot(theRoot);
    }

    @FXML
    private void clickOptionsBtn(ActionEvent event) throws Exception{
        System.out.println("gui.Controller.clickOptionsBtn()");
        Scene theScene = optionsBtn.getScene();
        Parent theRoot = FXMLLoader.load(getClass().getResource("../option/OptionView.fxml"));
        theScene.setRoot(theRoot);
    }

    @FXML
    private void clickExitBtn(ActionEvent event){
        System.out.println("gui.Controller.clickExitBtn()");
        Stage theStage = (Stage)exitBtn.getScene().getWindow();
        theStage.close();
    }
}
