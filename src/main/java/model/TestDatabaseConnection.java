package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.DatabaseUtility;

public class TestDatabaseConnection {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/mytickez?serverTimezone=Europe/Rome";
        String user = "root";
        String password = "pennare77a";

        System.out.println("Starting database connection test...");
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Direct database connection successful.");
            } else {
                System.out.println("Direct database connection failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error during direct database connection.");
            e.printStackTrace();
        }
    }
}


