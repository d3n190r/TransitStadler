package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Operator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class OperatorDao {
    private static final String operatorTableName = "operator";

    /**
     * static class -> private constructor
     */
    private OperatorDao() {
        // TODO: error
        System.err.println("[OperatorDao.OperatoDao()] An instance of OperatorDao was created.");
    }

    /**
     * Helper method to convert the result from the database to businessobjects.
     * @param dbSet The Resultset to convert, the method will convert the full set.
     * @return The ResultSet converted to a List.
     */
    private static List<Operator> convertDbResultToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Operator> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Operator(dbSet.getInt("operatorId"), dbSet.getString("operatorName")));
            }
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[OperatorDao.convertDbResultToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    /**
     * Creates a new operator in the database based on the given Operator object.
     * @param newOperator The operator that needs to be added to the database.
     * @return Whether the insert was successful.
     */
    public static boolean create(Operator newOperator) {
        Object[] parameterValues = new Object[] {operatorTableName, newOperator.operatorName()};
        if (Database.executeAny("INSERT INTO ? (operatorName) VALUES ?", parameterValues)) {
            return true;
        } else {
            // TODO: error
            System.err.println("[OperatorDao.create()] Could not create new operator.");
            return false;
        }
    }

    // TODO: documentation
    public static Operator read(int operatorId) {
        // TODO: implementation
        return null;
    }

    // TODO: documentation
    public static List<Operator> readAll() {
        // TODO: implementation
        return null;
    }

    // TODO: documentation
    public static void update(Operator updatedOperator) {
        // TODO: implementation
    }

    // TODO: documentation
    public static void delete(int operatorId) {
        // TODO: implementation
    }
}
