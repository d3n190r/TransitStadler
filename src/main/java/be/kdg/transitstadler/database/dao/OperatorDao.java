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
        Object[] parameterValues = new Object[] {newOperator.operatorName()};
        if (Database.executeAny("INSERT INTO " + operatorTableName + "(operatorName) VALUES (?)", parameterValues)) {
            return true;
        } else {
            // TODO: error
            System.err.println("[OperatorDao.create()] Could not create new operator.");
            return false;
        }
    }

    /**
     * Reads the information of the operator with the given id from the database.
     * @param operatorId The id of the operator to find the information.
     * @return An Operator object with the information on the requested operator.
     */
    public static Operator read(int operatorId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + operatorTableName + " WHERE operatorId = ?", new Object[] {operatorId});
        List<Operator> result = convertDbResultToObjectList(resultRows);
        if (result == null) {
            // TODO: error
            System.err.println("[OperatorDao.read()] Could not find operator in the database width id " + operatorId);
            return null;
        } else if (result.size() != 1) {
            // TODO: error
            System.err.println("[OperatorDao.read()] Found multiple operators in the database with id " + operatorId);
        }
        return result.getFirst();
    }

    /**
     * Reads all the operators from the database and returns them in a list.
     * @return A list with all the operators from the database.
     */
    public static List<Operator> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM" + operatorTableName);
        return convertDbResultToObjectList(resultRows);
    }

    /**
     * Updates the operator with the same id as the given Operator object to match the given Operator object.
     * @param updatedOperator What the operator should look like after the update.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean update(Operator updatedOperator) {
        Object[] parameterValues = new Object[] {updatedOperator.operatorName(), updatedOperator.operatorId()};
        int changes = Database.executeChange("UPDATE " + operatorTableName + " SET operatorName = ? WHERE operatorId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[OperatorDao.update()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[OperatorDao.update()] Somehow multiple operators with the same id exist in the database.");
        }
        return false;
    }

    /**
     * Deletes the operator with the requested id from the database.
     * @param operatorId The id of the operator to delete.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean delete(int operatorId) {
        int changes = Database.executeChange("DELETE FROM " + operatorTableName + " WHERE operatorId = ?", new Object[] {operatorId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[OperatoDao.delete()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[OperatorDao.delete()] Somehow multiple operators with the same id exist in the database.");
        }
        return false;
    }
}
