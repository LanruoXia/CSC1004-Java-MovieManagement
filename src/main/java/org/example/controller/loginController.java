package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.example.utils.*;

public class loginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label errorInfo;

    @FXML
    private Button cancelButton;

    @FXML
    private CheckBox isAdmin;

    public void initialize(){

    }
    @FXML
    public void handleLogin(){
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String passwordText = password.getText();
        String accountText = username.getText();
        if(!StringUtil.isEmpty(accountText) && !StringUtil.isEmpty(passwordText)){
            verifyLogin();
        }
        if(StringUtil.isEmpty(accountText)){
            errorInfo.setText("please enter your username");
            errorInfo.setVisible(true);
            return;
        }
        else if (StringUtil.isEmpty(passwordText)){
            errorInfo.setText("please enter your password");
            errorInfo.setVisible(true);
            return;
        }
        if(accountText.equals("admin") && passwordText.equals("admin") && isAdmin.isSelected()) {
            System.out.println("admin enters");
        }
//        }else {
//            errorInfo.setText("invalid username/password");
//            errorInfo.setVisible(true);
//        }

    }
    public void verifyLogin(){
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        String verify = "select count(1) from useraccounts where username = '"+username.getText()+"' and password = '"+password.getText()+"'";

        try{
            System.out.println(1111);
            Statement statement = dbConn.createStatement();
            ResultSet result = statement.executeQuery(verify);
            while(result.next()){
                if(result.getInt(1) == 1){
                    errorInfo.setText("Welcome!");
                    errorInfo.setVisible(true);

                }else{
                    errorInfo.setText("Invalid login. Please try again!");
                    errorInfo.setVisible(true);
                }
            }

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @FXML
    public void handleCancel(){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

}
