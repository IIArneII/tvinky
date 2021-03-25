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
        Scene theScene = backBtn.getScene();
        Parent theRoot = FXMLLoader.load(getClass().getResource("View.fxml"));
        theScene.setRoot(theRoot);
    }
}
