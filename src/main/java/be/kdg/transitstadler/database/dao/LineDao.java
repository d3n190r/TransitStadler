package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Line;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineDao {
    private static final String lineTableName = "line";
    private static final String stopsTableName = "stop";

    private LineDao() {System.err.println("[LineDao.LineDao()] An instance of LineDao was created.");}

    private static List<Line> convertDbResultSetToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Line> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Line(dbSet.getInt("lineId"), dbSet.getString("lineName"), dbSet.getInt("operatorId")));
            }
        } catch (SQLException e) {
            System.err.println("[LineDao.convertDbResultSetToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    public static boolean create(Line newLine) {
        Object[] parameterValues = new Object[] {newLine.lineName(), newLine.operatorId()};
        if (Database.executeAny("INSERT INTO " + lineTableName + "(lineName, operatorId) VALUES (?, ?)", parameterValues)) {
            return true;
        } else {
            System.err.println("[LineDao.create()] Could not create new line.");
            return false;
        }
    }

    public static Line read(int lineId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + lineTableName + " WHERE lineId = ?", new Object[] {lineId});
        List<Line> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            System.err.println("[LineDao.read()] Could not find line in the database with id " + lineId);
            return null;
        } else if (result.size() != 1) {
            System.err.println("[LineDao.read()] Found multiple lines in the database with id " + lineId);
        }
        return result.getFirst();
    }

    public static List<Line> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + lineTableName);
        return convertDbResultSetToObjectList(resultRows);
    }

    public static boolean update(Line updatedLine) {
        Object[] parameterValues = new Object[] {updatedLine.lineName(), updatedLine.operatorId(), updatedLine.lineId()};
        int changes = Database.executeChange("UPDATE " + lineTableName + " SET lineName = ?, operatorId = ? WHERE lineId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[LineDao.update()] No changes were made in the database.");
        } else {
            System.err.println("[LineDao.update()] Somehow multiple lines with the same id exist in the database.");
        }
        return false;
    }

    public static boolean delete(int lineId) {
        int changes = Database.executeChange("DELETE FROM " + lineTableName + " WHERE lineId = ?", new Object[] {lineId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[LineDao.delete()] No changes were made in the database.");
        } else {
            System.err.println("[LineDao.delete()] Somehow multiple lines with the same id exist in the database.");
        }
        return false;
    }

    public static List<Line> allLinesOfOperator(int operatorId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + lineTableName + " WHERE operatorId = ?", new Object[] {operatorId});
        return convertDbResultSetToObjectList(resultRows);
    }

    public static List<Line> allLinesAtStation(int stationId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + lineTableName + " WHERE EXISTS (SELECT 'x' FROM " + stopsTableName + " WHERE stop.lineId = line.lineId AND stop.stationId = ?)", new Object[] {stationId});
        return convertDbResultSetToObjectList(resultRows);
    }

    public static boolean deleteStop(int lineId, int stationId) {
        return Database.executeChange("DELETE FROM " + stopsTableName + " WHERE lineId = ? AND stationId = ?", new Object[] {lineId, stationId}) == 1;
    }

    public static boolean addStop(int lineId, int stationId) {
        try {
            ResultSet result = Database.executeQuery("SELECT MAX(stopSequence) FROM " + stopsTableName + " WHERE lineId = ?", new Object[]{lineId});
            result.next();
            int i = result.getInt(1) + 1;
            return Database.executeAny("INSERT INTO " + stopsTableName + "(lineId, stationId, stopsequence) VALUES (?, ?, ?)", new Object[] {lineId, stationId, i+1});
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
