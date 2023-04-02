package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;
import org.example.utils.ViewUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

//import org.example.utils.*;

public class loginController {

//    private static Stage stage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label infoLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private CheckBox isAdmin;

    @FXML
    private Label signUp;

    public void initialize() {

    }

    @FXML
    public void handleLogin() throws InterruptedException {
//        JdbcUtils jdbcUtils = new JdbcUtils();
//        jdbcUtils.getConnection();
        String passwordText = password.getText();
        String accountText = username.getText();
        if (!StringUtil.isEmpty(accountText) && !StringUtil.isEmpty(passwordText)) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            verifyLogin();
        }
        if (StringUtil.isEmpty(accountText)) {
            infoLabel.setText("please enter your username");
            infoLabel.setVisible(true);
            return;
        } else if (StringUtil.isEmpty(passwordText)) {
            infoLabel.setText("please enter your password");
            infoLabel.setVisible(true);
            return;
        }
        if (accountText.equals("1") && passwordText.equals("1") && isAdmin.isSelected()) {
            JdbcUtils jdbcUtils = new JdbcUtils();
            jdbcUtils.getConnection();
            System.out.println("admin enters");
            infoLabel.setText("Welcome, admin");
            infoLabel.setVisible(true);
            TimeUnit.SECONDS.sleep(1);
            enterAdminView();

        }
//        }else {
//            errorInfo.setText("invalid username/password");
//            errorInfo.setVisible(true);
//        }

    }

    public void verifyLogin() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        String verify = "select count(1) from useraccounts where username = '" + username.getText() + "' and password = '" + password.getText() + "'";

        try {
            System.out.println(1111);
            Statement statement = dbConn.createStatement();
            ResultSet result = statement.executeQuery(verify);
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    infoLabel.setText("Welcome!");
                    infoLabel.setVisible(true);

                } else {
                    infoLabel.setText("Invalid login. Please try again!");
                    infoLabel.setVisible(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void creatRegistration() {
        handleCancel();
        ViewUtils.openView("view/register.fxml");

    }

    public void enterAdminView(){
        handleCancel();
        ViewUtils.openView("view/adminView.fxml");
    }
}


