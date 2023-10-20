package com.techlabs.controllers;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/BANK";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lalu1998@";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
      
    }
}




