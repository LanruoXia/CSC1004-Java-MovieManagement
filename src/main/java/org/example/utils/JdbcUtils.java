package org.example.utils;

import java.sql.*;

public class JdbcUtils {
    private static final String USER_NAME = "user1";
    private static final String PASSWORD = "Abc1234!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/movieadmin";
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
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
    public static ResultSet getQueryResult(String stmt) throws SQLException {
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        Statement statement = dbConn.createStatement();
        ResultSet result = statement.executeQuery(stmt);
        return result;
    }
    public static boolean executeQueryStmt(String stmt) throws SQLException{
        JdbcUtils jdbcUtils = new JdbcUtils();
        Connection dbConn = jdbcUtils.getConnection();
        Statement statement = dbConn.createStatement();
        return statement.execute(stmt);

    }



}
