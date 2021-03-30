package com.company;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class Main extends Application{
    public static void main(String[] args) {
        System.out.println("Main.main()");
	    Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        System.out.println("Main.start()");
        Parent root = FXMLLoader.load(getClass().getResource("gui/main/View.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Tvinky");
        stage.setWidth(800);
        stage.setHeight(500);

        stage.show();
    }
}
