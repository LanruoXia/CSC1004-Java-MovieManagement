package org.example;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;

public class Main extends Application{
    private static Stage stage;
    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        stage.setTitle("MovieCan");
        changeView("view/login.fxml");
        stage.show();
    }

    public static void changeView(String fxmlFile){
        Parent root = null;
        try {
            System.out.println(Main.class.getClassLoader().getResource(fxmlFile));
            root = FXMLLoader.load(Main.class.getClassLoader().getResource(fxmlFile));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}