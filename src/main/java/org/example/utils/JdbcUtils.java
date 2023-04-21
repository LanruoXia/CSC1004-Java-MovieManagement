package org.example.utils;

import java.sql.*;

public class JdbcUtils {
    private static final String USER_NAME = "user1";
    private static final String PASSWORD = "Abc1234!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/movieadmin";
    private static Connection connection;

    public JdbcUtils(){
        try {
            Class.forName(DRIVER);
            System.out.println("DB connected");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *get connection to the database
     *
     */
    public Connection getConnection(){
        System.out.println("Connecting to the database...");
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
    public static ResultSet getQueryResult(String stmt) throws SQLException {
        try {

            System.out.println("Connected database successfully...");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(stmt);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean executeQueryStmt(String stmt) throws SQLException{

        try {

            System.out.println("Connected database successfully...");
            Statement statement = connection.createStatement();
            return statement.execute(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }



}
