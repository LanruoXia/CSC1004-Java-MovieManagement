package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.utils.ViewUtils;

public class popUpWindow {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSignOut;

    private adminViewController adminController;



    private static Stage adminStage;

    //renamePoster.fxml
    @FXML
    private Button renameConfirmButton, renameCancelButton;

    @FXML
    private Label imageFormat;

    @FXML
    private TextField imageText;

    private static String originalName;


    public static void getAdmin(Stage stage){
        adminStage = stage;


    }
    public void signOut(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        adminStage.close();
        ViewUtils.openView("view/login.fxml");


    }
    public static void getOriginalName(String fileName){//get original file name from adminViewController
        originalName = fileName;
    }



    public void renamePoster(){
        if(originalName.contains(".jpg")){
            imageFormat.setText(".jpg");
        }else if(originalName.contains(".png")){
            imageFormat.setText(".png");
        }
        String name = imageText.getText() + imageFormat.getText();
        System.out.println(imageFormat.getText());
        System.out.println(imageText.getText());
        adminViewController.getImageName(name);
        reviewCreatingController.getImageName(name);
        System.out.println(name);
        closeRename();

    }
    public void closeRename(){
        Stage stage = (Stage) renameCancelButton.getScene().getWindow();
        stage.close();
        
    }

}
