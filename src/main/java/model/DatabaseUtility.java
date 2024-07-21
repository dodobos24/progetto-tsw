package model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseUtility {
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/mytickez");
            if (dataSource != null) {
                System.out.println("DataSource initialized successfully.");
            } else {
                System.out.println("Failed to initialize DataSource.");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is null. Check your configuration.");
        }
        return dataSource.getConnection();
    }
}
