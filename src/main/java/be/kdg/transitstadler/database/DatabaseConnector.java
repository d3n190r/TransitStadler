package be.kdg.transitstadler.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Igor Goossens (INF 101)
 */
public class DatabaseConnector {
    private static Connection connection = null;

    private static String serverLocation = "jdbc:hsqldb:file:Database/TransitStadler";
    private static String username = "sa";
    private static String password = "";

    /**
     * static class -> private constructor
     */
    private DatabaseConnector() {
        // TODO: error
        System.err.println("[DatabaseConnector.DatabaseConnector()] An instance of DatabaseConnector was created.");
    }

    /**
     * Establishes a connection with the database if none is present and returns it.
     * @return A connection with the database.
     */
    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            connection = DriverManager.getConnection(serverLocation, username, password);
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[DatabaseConnector.getConnection()] An error occurred while trying to create the connection.");
            connection = null;
        }
        return connection;
    }

    /**
     * Closes the currently open connection with the database, if it exists.
     */
    public static void closeConnection() {
        try {
            if (connection == null) {
                return;
            }else if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[DatabaseConnector.closeConnection()] An error occurred while closing the connection.");
        }
        connection = null;
    }

    /**
     * Sets the serverlocation for new connections. The default value is "jdbc:hsqldb:file:Database/TransitStadler".
     * @param newServerLocation The new location to use.
     */
    public void setServerLocation(String newServerLocation) {
        serverLocation = newServerLocation;
    }

    /**
     * Sets the username for new connections. The default value is "sa".
     * @param newUsername The new username to use.
     */
    public void setUsername(String newUsername) {
        username = newUsername;
    }

    /**
     * Sets the password for the new connections. The default value is "".
     * @param newPassword The new password to use.
     */
    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
