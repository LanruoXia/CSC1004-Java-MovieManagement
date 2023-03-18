package org.example.utils;

import java.sql.*;

public class JdbcUtils {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "x20030926@X";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private Connection connection;

    public JdbcUtils(){
        try {
            Class.forName(DRIVER);
            System.out.println("DB connected");
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
        try(Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);) {
            System.out.println("Connected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }


}
