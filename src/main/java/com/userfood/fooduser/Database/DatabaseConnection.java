package com.userfood.fooduser.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/NetCafeDB";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
