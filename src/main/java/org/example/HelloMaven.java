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

import java.sql.SQLOutput;

public class HelloMaven extends Application{
    public static void main(String[] args){
        System.out.println("Hello, Maven!");
        launch(args);
    }
//    public void initiate() throws Exception{
//        System.out.println("initiate");
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("This is start()");
        primaryStage.setTitle("MovieCan");
        primaryStage.getIcons().add(new Image("D:\\A Coding Space\\CSC1004\\CSC1004-Java-MovieManagement\\src\\image\\icon.png"));
//        primaryStage.setWidth(500);
//        primaryStage.setHeight(500);
//        //primaryStage.setResizable(false);//不能拉伸改变stage大小
//        primaryStage.setMaxHeight(800);
       // primaryStage.setFullScreen(true);
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("Current Height = " + t1.doubleValue());
            }
        });
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("Current Width = " + t1.doubleValue());
            }
        });

        primaryStage.setScene(new Scene(new Group()));// Scene 必须有
        primaryStage.show();

    }
    public void stop() throws Exception{
        System.out.println("Closed");

    }
}
