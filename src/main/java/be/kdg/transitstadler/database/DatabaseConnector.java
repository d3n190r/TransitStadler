package be.kdg.transitstadler.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection = null;

    private static String serverLocation = "jdbc:hsqldb:file:Database/TransitStadler";
    private static String username = "sa";
    private static String password = "";

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
            // TODO: show error ≃
            //       DEVELOPER: "[yyyy/mm/dd hh:mm:ss - DatabaseConnector.getConnection()] Could not establish a connection with the Database"
            //       NORMAL: "[mm/dd hh:mm] Could not establish a connection with the Database"
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
            // TODO: show error ≃
            //       DEVELOPER: "[yyyy/mm/dd hh:mm:ss - DatabaseConnector.closeConnection()] Could not close the connection with the Database"
            //       NORMAL: "[mm/dd hh:mm] Could not close the connection with the Database"
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
