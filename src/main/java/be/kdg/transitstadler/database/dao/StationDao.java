package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Station;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDao {
    private static final String stationTableName = "station";

    private StationDao() {System.err.println("[StationDao.StationDao()] An instance of StationDao was created.");}

    private static List<Station> convertDbResultSetToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Station> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Station(dbSet.getInt("stationId"), dbSet.getString("stationName")));
            }
        } catch (SQLException e) {
            System.err.println("StationDao.convertDbResultSetToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    public static boolean create(Station newStation) {
        Object[] parameterValues = new Object[] {newStation.stationName()};
        if (Database.executeAny("INSERT INTO " + stationTableName + "(stationName) VALUES (?)", parameterValues)) {
            return true;
        } else {
            System.err.println("[StationDao.create()] Could not create new station.");
            return false;
        }
    }

    public static Station read(int stationId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName + " WHERE stationId = ?", new Object[] {stationId});
        List<Station> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            System.err.println("[stationDao.read()] Could not find station in the database width id " + stationId);
            return null;
        } else if (result.size() != 1) {
            System.err.println("[stationDao.read()] Found multiple stations in the database with id " + stationId);
        }
        return result.getFirst();
    }

    public static List<Station> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName);
        return convertDbResultSetToObjectList(resultRows);
    }

    public static boolean update(Station updatedStation) {
        Object[] parameterValues = new Object[] {updatedStation.stationName(), updatedStation.stationId()};
        int changes = Database.executeChange("UPDATE " + stationTableName + " SET stationName = ? WHERE stationId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[StationDao.update()] No changes were made in the database.");
        } else {
            System.err.println("[StationDao.update()] Somehow multiple stations with the same id exist in the database.");
        }
        return false;
    }

    public static boolean delete(int stationId) {
        int changes = Database.executeChange("DELETE FROM " + stationTableName + " WHERE stationId = ?", new Object[] {stationId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[StationDao.delete()] No changes were made in the database.");
        } else {
            System.err.println("[StationDao.delete()] Somehow multiple stations with the same id exist in the database.");
        }
        return false;
    }

    public static List<Station> allStationsOnLine(int lineId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + stationTableName + " WHERE EXISTS (SELECT 'x' FROM stop WHERE stop.stationId = station.stationId AND stop.lineId = ?)", new Object[] {lineId});
        return convertDbResultSetToObjectList(resultRows);
    }
}
