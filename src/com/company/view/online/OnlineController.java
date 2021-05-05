package com.company.view.online;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

public class OnlineController{
    @FXML
    private Button backBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private ProgressIndicator progressSearch;

    @FXML
    private void clickBackBtn(ActionEvent event) throws Exception{
        System.out.println("gui.OnlineController.clickBackBtn()");
        Scene theScene = backBtn.getScene();
        Parent theRoot = FXMLLoader.load(getClass().getResource("../main/MainView.fxml"));
        theScene.setRoot(theRoot);
    }

    @FXML
    private void clickSearchBtn(ActionEvent event){
        System.out.println("gui.OnlineController.clickSearchBtn()");
        progressSearch.setVisible(true);
    }
}
