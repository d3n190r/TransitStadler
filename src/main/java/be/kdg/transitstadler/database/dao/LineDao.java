package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Line;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class LineDao {
    private static final String lineTableName = "line";

    /**
     * static class -> private constructor
     */
    private LineDao() {
        // TODO: error
        System.err.println("[LineDao.LineDao()] An instance of LineDao was created.");
    }

    /**
     * Helper method to convert the result from the database to businessobjects.
     * @param dbSet The ResultSet to convert, the method will convert the full set.
     * @return The ResultSet converted to a List.
     */
    private static List<Line> convertDbResultSetToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Line> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Line(dbSet.getInt("lineId"), dbSet.getString("lineName"), dbSet.getInt("operatorName")));
            }
        } catch (SQLException e) {
            // TODO: error
            System.err.println("[LineDao.convertDbResultSetToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    /**
     * Creates a new line in the database based on the given Line object.
     * @param newLine The line that needs to be added to the database.
     * @return Whether the insert was successful.
     */
    public static boolean create(Line newLine) {
        Object[] parameterValues = new Object[] {newLine.lineName(), newLine.operatorId()};
        if (Database.executeAny("INSERT INTO " + lineTableName + " (lineName, operatorName) VALUES (?)", parameterValues)) {
            return true;
        } else {
            // TODO: error
            System.err.println("[LineDao.create()] Could not create new line.");
            return false;
        }
    }

    /**
     * Reads the information of the line with the given id from the database.
     * @param lineId The id of the line to find the information.
     * @return A Line object with the information on the requested line.
     */
    public static Line read(int lineId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM" + lineTableName + "WHERE lineId = ?", new Object[] {lineId});
        List<Line> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            // TODO: error
            System.err.println("[LineDao.read()] Could not find line in the database with id " + lineId);
            return null;
        } else if (result.size() != 1) {
            // TODO: error
            System.err.println("[LineDao.read()] Found multiple lines in the database with id " + lineId);
        }
        return result.getFirst();
    }

    /**
     * Reads all the lines from the database and returns them in a list.
     * @return A list with all the lines from the database.
     */
    public static List<Line> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + lineTableName);
        return convertDbResultSetToObjectList(resultRows);
    }

    /**
     * Updates the line with the same id as the given Line object to match the given Line object.
     * @param updatedLine What the line should look like after the update.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean update(Line updatedLine) {
        Object[] parameterValues = new Object[] {updatedLine.lineName(), updatedLine.operatorId(), updatedLine.lineId()};
        int changes = Database.executeChange("UPDATE " + lineTableName + " SET lineName = ?, operatorName = ? WHERE lineId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[LineDao.update()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[LineDao.update()] Somehow multiple lines with the same id exist in the database.");
        }
        return false;
    }

    /**
     * Deletes the line with the requested id from the database.
     * @param lineId The id of the line to delete.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean delete(int lineId) {
        int changes = Database.executeChange("DELETE FROM " + lineTableName + " WHERE lineId = ?", new Object[] {lineId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[LineDao.delete()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[LineDao.delete()] Somehow multiple lines with the same id exist in the database.");
        }
        return false;
    }
}
