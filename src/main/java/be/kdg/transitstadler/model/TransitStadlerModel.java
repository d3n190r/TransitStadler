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
     */
    public void addLine(String lineName, int operatorId) {
        LineDao.create(new Line(-1, lineName, operatorId));
    }

    /**
     * Returns all the info in the database of a line based on the given id.
     *
     * @param lineId The id of the line of interest.
     * @return A Line object with the information of the requested line.
     */
    public Line getLineInfo(int lineId) {
        return LineDao.read(lineId);
    }

    /**
     * Returns all the lines in the database.
     *
     * @return List with all the lines from the database.
     */
    public List<Line> getAllLines() {
        return LineDao.readAll();
    }

    /**
     * Updates the information of an existing line in the database.
     *
     * @param lineId The id of the line that should be changed.
     * @param newLineName The new name of the line.
     */
    public boolean updateLine(int lineId, String newLineName) {
        // TODO: update documentation
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            // TODO: show error ≃ "Could not update line with lineId: {lineId}. Does it exist in the database?"
            return false;
        }
        return updateLine(lineId, newLineName, oldLine.operatorId());
    }

    /**
     * Updates the information of an existing line in the database.
     *
     * @param lineId The id of the line that should be changed.
     * @param newOperatorId The new operator associated with the line.
     */
    public boolean updateLine(int lineId, int newOperatorId) {
        // TODO: update documentation
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            // TODO: show error ≃ "Could not update line with lineId: {lineId}. Does it exist in the database?"
            return false;
        }
        return updateLine(lineId, oldLine.lineName(), newOperatorId);
    }

    /**
     * Updates the information of an existing line in the database.
     *
     * @param lineId The id of the line that needs to be changed.
     * @param newLineName The new name of the line.
     * @param newOperatorId The new operator associated with the line.
     */
    public boolean updateLine(int lineId, String newLineName, int newOperatorId) {
        // TODO: update documentation
        return LineDao.update(new Line(lineId, newLineName, newOperatorId));
    }

    /**
     * Deletes the line from the database.
     *
     * @param lineId The id of the line that needs to be deleted.
     */
    public void deleteLine(int lineId) {
        // TODO: make boolean (+docuementation)
        LineDao.delete(lineId);
    }


    /**
     * Adds a new station to the database.
     *
     * @param stationName The name of the new station.
     */
    public void addStation(String stationName) {
        StationDao.create(new Station(-1, stationName));
    }

    /**
     * Returns all the info in the database of a station based on the given id.
     *
     * @param stationId The id of the station of interest.
     * @return A Station object with the information of the requested station.
     */
    public Station getStationInfo(int stationId) {
        return StationDao.read(stationId);
    }

    /**
     * Returns all the stations in the database.
     *
     * @return List with all the stations from the database.
     */
    public List<Station> getAllStations() {
        return StationDao.readAll();
    }

    /**
     * Updates the information of an existing station in the database.
     *
     * @param stationId The id of the station that needs to be changed.
     * @param newStationName The new name of the station.
     */
    public void updateStation(int stationId, String newStationName) {
        // TODO: make boolean (+documentation)
        StationDao.update(new Station(stationId, newStationName));
    }

    /**
     * Deletes the station from the database.
     *
     * @param stationId The id of the station that needs to be deleted.
     */
    public void deleteStation(int stationId) {
        // TODO: make boolean (+docuementation)
        StationDao.delete(stationId);
    }


    /**
     * Adds a new operator to the database.
     *
     * @param operatorName The name of the new operator.
     */
    public void addOperator(String operatorName) {
        OperatorDao.create(new Operator(-1, operatorName));
    }

    /**
     * Returns all the info in the database of an operator based on the given id.
     *
     * @param operatorId The id of the operator of interest.
     * @return A Operator object with the information of the requested operator.
     */
    public Operator getOperatorInfo(int operatorId) {
        return OperatorDao.read(operatorId);
    }

    /**
     * Returns all the operators in the database.
     *
     * @return List with all the operators from the database.
     */
    public List<Operator> getAllOperators() {
        return OperatorDao.readAll();
    }

    /**
     * Updates the information of an existing operator in the database.
     *
     * @param operatorId The id of the operator that needs to be changed.
     * @param newOperatorName The new name of the operator.
     */
    public void updateOperator(int operatorId, String newOperatorName) {
        // TODO: make boolean (+documentation)
        OperatorDao.update(new Operator(operatorId, newOperatorName));
    }

    /**
     * Deletes the station from the database.
     *
     * @param operatorId The id of the operator that needs to be deleted.
     */
    public void deleteOperator(int operatorId) {
        // TODO: make boolean (+docuementation)
        OperatorDao.delete(operatorId);
    }
}
