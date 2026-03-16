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
        // TODO: show error
    }

    /**
     * Helper method to convert the result from the database to businessobjects.
     * @param dbSet The ResultSet to convert, the method will convert the full list.
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
            // TODO: show error ≃ "Could not convert result from a query on the database."
            return null;
        }
        return result;
    }

    // TODO: documentation
    public static boolean create(Line newLine) {
        Object[] parameterValues = new Object[] {lineTableName, newLine.lineName(), newLine.operatorId()};
        return Database.executeAny("INSERT INTO ? (lineName, operatorName) VALUES ?", parameterValues);
    }

    // TODO: documentation
    public static Line read(int lineId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM ? WHERE lineId = ?", new Object[] {lineTableName, lineId});
        List<Line> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            // TODO: show error ≃ "Could not find a line associated with lineId: {lineId}."
            return null;
        } else {
            return result.getFirst();
        }
    }

    // TODO: documentation
    public static List<Line> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM ?", new Object[] {lineTableName});
        return convertDbResultSetToObjectList(resultRows);
    }

    // TODO: documentation
    public static boolean update(Line updatedLine) {
        Object[] parameterValues = new Object[] {lineTableName, updatedLine.lineName(), updatedLine.operatorId(), updatedLine.lineId()};
        int changes = Database.executeChange("UPDATE ? SET lineName = ?, operatorName = ? WHERE lineId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 0) {
            // TODO: show error ≃ Something went wrong
        }else if (changes > 1) {
            // TODO: show error ≃ "Something is wrong with lineId in the database"
        }
        return false;
    }

    // TODO: documentation
    public static boolean delete(int lineId) {
        int changes = Database.executeChange("DELETE FROM ? WHERE lineId = ?", new Object[] {lineTableName, lineId});
        if (changes == 1) {
            return true;
        } else if (changes == 0) {
            return false;
        } else {
            // TODO: show error ≃ "Something is wrong with lineId in the database"
            return false;
        }
    }
}
