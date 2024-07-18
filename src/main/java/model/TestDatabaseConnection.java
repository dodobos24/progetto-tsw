package model;

import java.sql.Connection;
import java.sql.SQLException;
import model.DatabaseUtility;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseUtility.getConnection();
            if (connection != null) {
                System.out.println("Connection established successfully.");
                connection.close();
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

