package org.example;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestController extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        System.out.println("This is start()");

        stage.setTitle("MovieCan");
        stage.getIcons().add(new Image("\\src\\image\\icon.png"));
        stage.setScene(new Scene(root));
        stage.show();


    }
}
