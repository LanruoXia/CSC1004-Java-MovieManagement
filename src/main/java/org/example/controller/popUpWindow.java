package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

//    public void injectAdminController(adminViewController adminViewController){
//        this.adminController = adminViewController;
//
//    }
    public static void getAdmin(Stage stage){
        adminStage = stage;


    }
    public void signOut(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        adminStage.close();
        ViewUtils.openView("view/login.fxml");


    }

}
