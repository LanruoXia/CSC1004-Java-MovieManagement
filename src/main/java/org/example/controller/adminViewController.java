package org.example.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.models.User;
import org.example.utils.JdbcUtils;
import org.example.utils.ViewUtils;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminViewController implements Initializable {

//    private popUpWindow popUp;
//
//    @FXML
//    private void init(){
//        popUp.injectAdminController(this);
//    }

    @FXML
    private HBox btnAdmin;

    @FXML
    private HBox btnRepo;

    @FXML
    private HBox btnSignOut;

    @FXML
    private HBox btnStats;

    @FXML
    private HBox btnUsers;

    @FXML
    private AnchorPane paneAdmin;

    @FXML
    private AnchorPane paneRepo;

    @FXML
    private AnchorPane paneSignOut;

    @FXML
    private AnchorPane paneStats;

    @FXML
    private AnchorPane paneUsers;


    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, Integer> ageColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, Integer> passwordColumn;
    @FXML
    private Button test;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set up the column of userTableView
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Address"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Password"));
        //load data
        try {
            userTableView.setItems(getUserList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        ResultSet rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.useraccounts");
        users.clear();
        while (rs.next()){
            User newuser = new User();
            newuser.setId(rs.getInt("iduseraccounts"));
            newuser.setUsername(rs.getString("username"));
            newuser.setAge(rs.getInt("age"));
            newuser.setGender(rs.getString("gender"));
            newuser.setAddress(rs.getString("address"));
            newuser.setPassword(rs.getInt("password"));
            users.add(newuser);
        }
        return users;


    }


    public void testCode() throws SQLException {
        ResultSet rs = null;
        try {
            rs = JdbcUtils.getQueryResult("SELECT * FROM movieadmin.useraccounts");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(111);
        while (rs.next()){
            User newuser = new User();
            newuser.setId(rs.getInt("iduseraccounts"));
            System.out.println("Hi");
            System.out.println(newuser.getId());
        }
    }


//    @FXML
//    public void handleClicks(javafx.event.ActionEvent event) {
//        if (event.getSource() == btnAdmin) {
//
//        } else if (event.getSource() == btnUsers) {
//
//
//        } else if (event.getSource() == btnRepo) {
//
//        } else if (event.getSource() == btnStats) {
//
//        }else if (event.getSource() == btnStats) {
//
//        }else if (event.getSource() == btnSignOut) {
//            System.out.println("hi");
//            ViewUtils.openView("view/SignOut.fxml");
//
//
//        }
//    }
    public void closeStage(){
        Stage stage = (Stage) btnSignOut.getScene().getWindow();
        stage.close();
    }

    public void clickUser(){
        paneUsers.toFront();
    }
    public void clickRepo(){
        paneRepo.toFront();

    }

    public void clickSignOut(){
        System.out.println("hi");
        ViewUtils.openView("view/SignOut.fxml");
        popUpWindow popController = new popUpWindow();
        popUpWindow.getAdmin((Stage) btnAdmin.getScene().getWindow());
    }
}




