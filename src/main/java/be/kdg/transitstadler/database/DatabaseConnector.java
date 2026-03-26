package be.kdg.transitstadler.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection = null;

    private static String serverLocation = "jdbc:hsqldb:file:Database/TransitStadler";
    private static String username = "sa";
    private static String password = "";

    private DatabaseConnector() {
        System.err.println("[DatabaseConnector.DatabaseConnector()] An instance of DatabaseConnector was created.");
    }

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            connection = DriverManager.getConnection(serverLocation, username, password);
        } catch (SQLException e) {
            System.err.println("[DatabaseConnector.getConnection()] An error occurred while trying to create the connection.");
            connection = null;
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection == null) {
                return;
            }else if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("[DatabaseConnector.closeConnection()] An error occurred while closing the connection.");
        }
        connection = null;
    }

    public static void setServerLocation(String newServerLocation) {serverLocation = newServerLocation;}

    public static void setUsername(String newUsername) {username = newUsername;}

    public static void setPassword(String newPassword) {password = newPassword;}
}
