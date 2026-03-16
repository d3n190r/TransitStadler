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
    private static List<Station> convertDbResultToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Station> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Station(dbSet.getInt("stationId"), dbSet.getString("stationName")));
            }
        } catch (SQLException e) {
            // TODO: error
            System.err.println("StationDao.convertDbResultToObjectList()] Could not convert result from database.");
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
        Object[] parameterValues = new Object[] {stationTableName, newStation.stationName()};
        if (Database.executeAny("INSERT INTO ? (stationName) VALUES ?", parameterValues)) {
            return true;
        } else {
            // TODO: error
            System.err.println("[StationDao.create()] Could not create new station.");
            return false;
        }
    }

    // TODO: documentation
    public static Station read(int stationId) {
        // TODO: implementation
        return null;
    }

    // TODO: documentation
    public static List<Station> readAll() {
        // TODO: implementation
        return null;
    }

    // TODO: documentation
    public static void update(Station updatedStation) {
        // TODO: implementation
    }

    // TODO: documentation
    public static void delete(int stationId) {
        // TODO: implementation
    }
}
