package be.kdg.transitstadler.database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Igor Goossens (INF 101)
 */
public class Database {
    /**
     * static class -> private constructor
     */
    private Database() {
        // TODO: error
        System.err.println("[Database.Database()] An instance of Database was created.");
    }

    /**
     * Prepares an statement to be executed on the database.
     * @param connection The connection with the database.
     * @param query The query to prepare.
     * @param parameterValues The values that need to be added to the query.
     * @return The query in PreparedStatement form.
     * @throws SQLException If the query is invalid, parameter binding fails or the connection with the database gets lost.
     */
    private static PreparedStatement prepareStatement(Connection connection, String query, Object[] parameterValues) throws SQLException {
        PreparedStatement result = connection.prepareStatement(query);
        for (int i = 0; i < parameterValues.length; i++) {
            result.setObject(i, parameterValues[i]);
        }
        return result;
    }

    /**
     * Executes a SELECT on the database.
     * @param query A SELECT query for the database, with a questionmark at parameter places.
     * @param parameterValues The values that need to be filled in at the questionmarks.
     * @return An set with all the rows the query returned.
     */
    public static ResultSet executeQuery(String query, Object[] parameterValues) {
        CachedRowSet cachedRowSet;
        Connection connection = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = prepareStatement(connection, query, parameterValues);
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(preparedStatement.executeQuery());

        } catch (SQLException e) {
            // TODO: error
            System.err.println("[Database.executeQuery()] Could not execute query");
            return null;
        } finally {
            DatabaseConnector.closeConnection();
        }
        return cachedRowSet;
    }

    /**
     * Executes a change query on the database (INSERT, UPDATE or DELETE).
     * @param query A query of the right type, with a questionmark at parameter places.
     * @param parameterValues The values that need to be filled in at the questionmarks.
     * @return An int with how many rows were changed. If it is < 0 something went wrong and nothing changed.
     */
    public static int executeChange(String query, Object[] parameterValues) {
        Connection connection = DatabaseConnector.getConnection();
        int result;

        try {
            result = prepareStatement(connection, query, parameterValues).executeUpdate();
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[Database.executeChange()] Could not execute query");
            return -1;
        }  finally {
            DatabaseConnector.closeConnection();
        }
        return result;
    }

    /**
     * Executes a query on the database.
     * @param query The query to be executed, with a questionmark at parameter places.
     * @param parameterValues The values that need to be filled in at the questionmarks.
     * @return An boolean indicating if the query ran succesfully.
     */
    public static boolean executeAny(String query, Object[] parameterValues) {
        Connection connection = DatabaseConnector.getConnection();
        boolean result;

        try {
            prepareStatement(connection, query, parameterValues).execute();
            result = true;
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[Database.executeAny()] Could not execute query");
            result = false;
        } finally {
            DatabaseConnector.closeConnection();
        }
        return result;
    }
}
