package com.hexaware.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pets";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DbConn() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
