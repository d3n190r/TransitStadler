package be.kdg.transitstadler.model;

import be.kdg.transitstadler.database.dao.LineDao;
import be.kdg.transitstadler.database.dao.OperatorDao;
import be.kdg.transitstadler.database.dao.StationDao;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;

import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class TransitStadlerModel {
    /**
     * Adds a new line to the database.
     * @param lineName The name of the new line.
     * @param operatorId The id associated with the operator of the new line.
     * @return Whether the insertion was successful.
     */
    public boolean addLine(String lineName, int operatorId) {
        return LineDao.create(new Line(-1, lineName, operatorId));
    }

    /**
     * Returns all the info in the database of a line based on the given id.
     * @param lineId The id of the line of interest.
     * @return A Line object with the information of the requested line.
     */
    public Line getLineInfo(int lineId) {
        return LineDao.read(lineId);
    }

    /**
     * Returns all the lines in the database.
     * @return List with all the lines from the database.
     */
    public List<Line> getAllLines() {
        return LineDao.readAll();
    }

    /**
     * Updates the information of an existing line in the database.
     * @param lineId The id of the line that should be changed.
     * @param newLineName The new name of the line.
     * @return Whether the update was successful.
     */
    public boolean updateLine(int lineId, String newLineName) {
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            return false;
        }
        return updateLine(lineId, newLineName, oldLine.operatorId());
    }

    /**
     * Updates the information of an existing line in the database.
     * @param lineId The id of the line that should be changed.
     * @param newOperatorId The new operator associated with the line.
     * @return Whether the update was successful.
     */
    public boolean updateLine(int lineId, int newOperatorId) {
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            return false;
        }
        return updateLine(lineId, oldLine.lineName(), newOperatorId);
    }

    /**
     * Updates the information of an existing line in the database.
     * @param lineId The id of the line that needs to be changed.
     * @param newLineName The new name of the line.
     * @param newOperatorId The new operator associated with the line.
     * @return Whether the update was successful.
     */
    public boolean updateLine(int lineId, String newLineName, int newOperatorId) {
        return LineDao.update(new Line(lineId, newLineName, newOperatorId));
    }

    /**
     * Deletes the line from the database.
     * @param lineId The id of the line that needs to be deleted.
     * @return Whether the deletion was successful.
     */
    public boolean deleteLine(int lineId) {
        return LineDao.delete(lineId);
    }


    /**
     * Adds a new station to the database.
     * @param stationName The name of the new station.
     * @return Whether the insertion was successful.
     */
    public boolean addStation(String stationName) {
        return StationDao.create(new Station(-1, stationName));
    }

    /**
     * Returns all the info in the database of a station based on the given id.
     * @param stationId The id of the station of interest.
     * @return A Station object with the information of the requested station.
     */
    public Station getStationInfo(int stationId) {
        return StationDao.read(stationId);
    }

    /**
     * Returns all the stations in the database.
     * @return List with all the stations from the database.
     */
    public List<Station> getAllStations() {
        return StationDao.readAll();
    }

    /**
     * Updates the information of an existing station in the database.
     * @param stationId The id of the station that needs to be changed.
     * @param newStationName The new name of the station.
     * @return Whether the update was successful.
     */
    public boolean updateStation(int stationId, String newStationName) {
        return StationDao.update(new Station(stationId, newStationName));
    }

    /**
     * Deletes the station from the database.
     * @param stationId The id of the station that needs to be deleted.
     * @return Whether the deletion was successful.
     */
    public boolean deleteStation(int stationId) {
        return StationDao.delete(stationId);
    }


    /**
     * Adds a new operator to the database.
     * @param operatorName The name of the new operator.
     * @return Whether the insertion was successful.
     */
    public boolean addOperator(String operatorName) {
        return OperatorDao.create(new Operator(-1, operatorName));
    }

    /**
     * Returns all the info in the database of an operator based on the given id.
     * @param operatorId The id of the operator of interest.
     * @return A Operator object with the information of the requested operator.
     */
    public Operator getOperatorInfo(int operatorId) {
        return OperatorDao.read(operatorId);
    }

    /**
     * Returns all the operators in the database.
     * @return List with all the operators from the database.
     */
    public List<Operator> getAllOperators() {
        return OperatorDao.readAll();
    }

    /**
     * Updates the information of an existing operator in the database.
     * @param operatorId The id of the operator that needs to be changed.
     * @param newOperatorName The new name of the operator.
     * @return Whether the update was successful.
     */
    public boolean updateOperator(int operatorId, String newOperatorName) {
        return OperatorDao.update(new Operator(operatorId, newOperatorName));
    }

    /**
     * Deletes the operator from the database.
     * @param operatorId The id of the operator that needs to be deleted.
     * @return Whether the deletion was successful.
     */
    public boolean deleteOperator(int operatorId) {
        return OperatorDao.delete(operatorId);
    }

    /**
     * Returns all the lines of a specified operator.
     * @param operatorId The id of the operator to find the lines of.
     * @return The lines of the specified operator.
     */
    public List<Line> getAllLinesByOperator(int operatorId) {
        return LineDao.allLinesOfOperator(operatorId);
    }

    /**
     * Returns all the lines of a specified station.
     * @param stationId The id of the station to find the lines of.
     * @return The lines of the specified station.
     */
    public List<Line> getAllLinesByStation(int stationId) {
        return LineDao.allLinesAtStation(stationId);
    }

    /**
     * Returns all the stations of a specified line.
     * @param lineId The id of the line to find the stations of.
     * @return The stations of the specified line.
     */
    public List<Station> getAllStationsByLine(int lineId) {
        return StationDao.allStationsOnLine(lineId);
    }

    public boolean deleteStop(int lineId, int stationId) {
        return LineDao.deleteStop(lineId, stationId);
    }

    public boolean addStop(int lineId, int stationId) {
        return LineDao.addStop(lineId, stationId);
    }
}
