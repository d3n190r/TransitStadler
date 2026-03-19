package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Station;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class StationDao {
    private static final String stationTableName = "station";

    /**
     * static class -> private constructor
     */
    private StationDao() {
        // TODO: error
        System.err.println("[StationDao.StationDao()] An instance of StationDao was created.");
    }

    /**
     * Helper method to convert the result from the database to businessobjects
     * @param dbSet The ResultSet to convert, the method will convert the full set.
     * @return The ResultSet converted to a List.
     */
    private static List<Station> convertDbResultSetToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Station> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Station(dbSet.getInt("stationId"), dbSet.getString("stationName")));
            }
        } catch (SQLException e) {
            // TODO: error
            System.err.println("StationDao.convertDbResultSetToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    /**
     * Creates a new station in the database based on the given Station object.
     * @param newStation The station that needs to be added to the database.
     * @return Whether the insert was successful.
     */
    public static boolean create(Station newStation) {
        Object[] parameterValues = new Object[] {newStation.stationName()};
        if (Database.executeAny("INSERT INTO " + stationTableName + "(stationName) VALUES (?)", parameterValues)) {
            return true;
        } else {
            // TODO: error
            System.err.println("[StationDao.create()] Could not create new station.");
            return false;
        }
    }

    /**
     * Reads the information of the station with the given id from the database.
     * @param stationId The id of the station to find the information.
     * @return An Station object with the information on the requested station.
     */
    public static Station read(int stationId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName + " WHERE stationId = ?", new Object[] {stationId});
        List<Station> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            // TODO: error
            System.err.println("[stationDao.read()] Could not find station in the database width id " + stationId);
            return null;
        } else if (result.size() != 1) {
            // TODO: error
            System.err.println("[stationDao.read()] Found multiple stations in the database with id " + stationId);
        }
        return result.getFirst();
    }

    /**
     * Reads all the stations from the database and returns them in a list.
     * @return A list with all the stations from the database.
     */
    public static List<Station> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName);
        return convertDbResultSetToObjectList(resultRows);
    }

    /**
     * Updates the station with the same id as the given Station object to match the given Station object.
     * @param updatedStation What the station should look like after the update.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean update(Station updatedStation) {
        Object[] parameterValues = new Object[] {updatedStation.stationName(), updatedStation.stationId()};
        int changes = Database.executeChange("UPDATE " + stationTableName + " SET stationName = ? WHERE stationId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[StationDao.update()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[StationDao.update()] Somehow multiple stations with the same id exist in the database.");
        }
        return false;
    }

    /**
     * Deletes the station with the requested id from the database.
     * @param stationId The id of the station to delete.
     * @return Whether the update was successful. This is also false if somehow multiple rows in the database were changed.
     */
    public static boolean delete(int stationId) {
        int changes = Database.executeChange("DELETE FROM " + stationTableName + " WHERE stationId = ?", new Object[] {stationId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            // TODO: error
            System.err.println("[StationDao.delete()] No changes were made in the database.");
        } else {
            // TODO: error
            System.err.println("[StationDao.delete()] Somehow multiple stations with the same id exist in the database.");
        }
        return false;
    }

    /**
     * Reads al the stations associated with the line with the given id.
     * @param lineId The id of the line whose stations need to be searched.
     * @return All the stations of the specified line.
     */
    public static List<Station> allStationsOnLine(int lineId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName + " WHERE EXISTS (SELECT 'x' FROM stop WHERE stop.stationId = station.stationId AND stop.lineId = ?)", new Object[] {lineId});
        return convertDbResultSetToObjectList(resultRows);
    }
}
