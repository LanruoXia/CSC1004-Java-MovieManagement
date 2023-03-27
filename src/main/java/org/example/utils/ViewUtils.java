package org.example.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.loginController;

import java.io.IOException;

public class ViewUtils {
    public static void openView(String fxmlfile){
        Parent root = null;
        try {
            root = FXMLLoader.load(loginController.class.getClassLoader().getResource(fxmlfile));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
