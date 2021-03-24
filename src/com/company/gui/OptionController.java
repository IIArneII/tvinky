package com.company.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.Thread;

public class OptionController {

    @FXML
    private Button backBtn;

    @FXML
    private void clickBackBtn(ActionEvent event) throws Exception{
        System.out.println("gui.OptionController.clickBackBtn()");
        Stage theStage = (Stage)backBtn.getScene().getWindow();
        Parent theRoot = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene theScene = new Scene(theRoot);
        theStage.setScene(theScene);
    }
}
