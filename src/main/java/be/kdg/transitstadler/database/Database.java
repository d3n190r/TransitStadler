package be.kdg.transitstadler.database;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private Database() {System.err.println("[Database.Database()] An instance of Database was created.");}

    private static PreparedStatement prepareStatement(Connection connection, String query, Object[] parameterValues) throws SQLException {
        PreparedStatement result = connection.prepareStatement(query);
        for (int i = 0; i < parameterValues.length; i++) {
            result.setObject(i+1, parameterValues[i]);
        }
        return result;
    }

    public static ResultSet executeQuery(String query) {return executeQuery(query, new Object[0]);}

    public static ResultSet executeQuery(String query, Object[] parameterValues) {
        CachedRowSet cachedRowSet;
        Connection connection = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = prepareStatement(connection, query, parameterValues);
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(preparedStatement.executeQuery());

        } catch (SQLException e) {
            System.err.println("[Database.executeQuery()] Could not execute query");
            return null;
        } finally {
            DatabaseConnector.closeConnection();
        }
        return cachedRowSet;
    }

    public static int executeChange(String query) {return executeChange(query, new Object[0]);}

    public static int executeChange(String query, Object[] parameterValues) {
        Connection connection = DatabaseConnector.getConnection();
        int result;

        try {
            result = prepareStatement(connection, query, parameterValues).executeUpdate();
        } catch (SQLException e) {
            System.err.println("[Database.executeChange()] Could not execute query");
            return -1;
        }  finally {
            DatabaseConnector.closeConnection();
        }
        return result;
    }

    public static boolean executeAny(String query) {return executeAny(query, new Object[0]);}

    public static boolean executeAny(String query, Object[] parameterValues) {
        Connection connection = DatabaseConnector.getConnection();
        boolean result;

        try {
            prepareStatement(connection, query, parameterValues).execute();
            result = true;
        } catch (SQLException e) {
            System.err.println("[Database.executeAny()] Could not execute query");
            result = false;
        } finally {
            DatabaseConnector.closeConnection();
        }
        return result;
    }
}
