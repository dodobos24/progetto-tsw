package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.DatabaseUtility;

public class TestDatabaseConnection {

	public static void main(String[] args) {
	    testConnection();
	}

	public static void testConnection() {
	    Connection connection = null;
	    try {
	        connection = DatabaseUtility.getConnection();
	        if (connection != null) {
	            System.out.println("Connessione al database stabilita.");
	            // Esegui qui una query di test per recuperare un dato dal database
	            String query = "SELECT * FROM Events LIMIT 1";
	            try (PreparedStatement statement = connection.prepareStatement(query);
	                 ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    System.out.println("Dato recuperato: " + resultSet.getString("event_name"));
	                } else {
	                    System.out.println("Nessun dato trovato.");
	                }
	            }
	        } else {
	            System.out.println("Connessione al database non riuscita.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}


