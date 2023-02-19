package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLOutput;

public class HelloMaven extends Application{
    public static void main(String[] args){
        System.out.println("Hello, Maven!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("This is start()");
        stage.setTitle("title");
        stage.show();

    }
}
