package org.example.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.utils.JdbcUtils;
import org.example.utils.StringUtil;
import org.example.utils.ViewUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registerController  implements Initializable {

    @FXML
    private Label signupInfo;

    @FXML
    private Label passwordInfo;

    @FXML
    private TextField setUsername;

    @FXML
    private Label usernameInfo;

    @FXML
    private TextField setAge;

    @FXML
    private ChoiceBox<String> genderChoice;

    private String[] gender = {"Female", "Male", "Nonbinary"};

    @FXML
    private TextField setAddress;

    @FXML
    private PasswordField setPassword;

    @FXML
    private PasswordField confirmPassword;


    @FXML
    private Button closeButton;


    /**
     * verify the registration conditions
     */
    public void handleRegister(){

        checkUsername();

        if (!StringUtil.isEmpty(setUsername.getText()) && !StringUtil.isEmpty(setAge.getText()) && !StringUtil.isEmpty(genderChoice.getValue()) && !StringUtil.isEmpty(setAddress.getText())&& !StringUtil.isEmpty(setPassword.getText())) {
            if (confirmPassword.getText().equals(setPassword.getText())) {
                setAccount();
            } else {
                passwordInfo.setText("Password does not match!");
                passwordInfo.setVisible(true);
            }
        } else {
            passwordInfo.setText("Please fill in all the information.");
            passwordInfo.setVisible(true);

        }

    }

    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        ViewUtils.openView("view/login.fxml");
    }

    /**
     * write information into user database and finish registration
     */
    public void setAccount() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        //get new user information from the form
        String username = setUsername.getText();
        String age = setAge.getText();
        String gender = genderChoice.getValue();
        String address = setAddress.getText();
        String password = confirmPassword.getText();

        String insertNewUser = "INSERT INTO movieadmin.useraccounts " + "(username, password, age, gender, address) " +
                "VALUES ('" + username + "','" + password + "','" + age + "','" + gender + "','" + address + "')";

        try {
            jdbcUtils.executeQueryStmt(insertNewUser);
            passwordInfo.setVisible(false);
            signupInfo.setText("Signed up successfully!");
            signupInfo.setVisible(true);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
    /**
     * check if the username already exists in database
     */
    public void checkUsername() {
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        String username = setUsername.getText();
        String verify = "SELECT EXISTS(SELECT 1 FROM movieadmin.useraccounts WHERE username = '"+username+"')";

        try {
//            System.out.println(1111);
//            Statement statement = dbConn.createStatement();
//            ResultSet result = statement.executeQuery(verify);

            ResultSet result = JdbcUtils.getQueryResult(verify);
            while (result.next()) {
                if (result.getInt(1) == 1) {
                    usernameInfo.setText("This username has already been taken.");
                    usernameInfo.setVisible(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoice.getItems().addAll(gender);

    }
}


