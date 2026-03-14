package be.kdg.transitstadler.database;

import java.sql.ResultSet;

/**
 * @author Igor Goossens (INF 101)
 */
public class Database {
    // TODO: attributes & constructor

    // TODO: documentation
    public static ResultSet executeQuery(String query) {return executeQuery(query, new Object[0]);}

    // TODO: documentation
    public static ResultSet executeQuery(String query, Object[] parameterValues) {
        // TODO: implementation
        return null;
    }

    // TODO: documentation
    public static int executeChange(String query) {return executeChange(query, new Object[0]);}

    // TODO: documentation
    public static int executeChange(String query, Object[] parameterValues) {
        // TODO: implementation
        return 0;
    }

    // TODO: documentation
    public static void executeAny(String query) {executeAny(query, new Object[0]);}

    // TODO: documentation
    public static void executeAny(String query, Object[] parameterValues) {
        // TODO: implementation
    }
}
