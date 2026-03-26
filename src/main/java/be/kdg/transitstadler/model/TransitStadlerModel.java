package be.kdg.transitstadler.model;

import be.kdg.transitstadler.database.dao.LineDao;
import be.kdg.transitstadler.database.dao.OperatorDao;
import be.kdg.transitstadler.database.dao.StationDao;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import java.util.List;

public class TransitStadlerModel {
    public boolean addLine(String lineName, int operatorId) {return LineDao.create(new Line(-1, lineName, operatorId));}

    public Line getLineInfo(int lineId) {return LineDao.read(lineId);}

    public List<Line> getAllLines() {return LineDao.readAll();}

    public boolean updateLine(int lineId, String newLineName) {
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            return false;
        }
        return updateLine(lineId, newLineName, oldLine.operatorId());
    }

    public boolean updateLine(int lineId, int newOperatorId) {
        Line oldLine = LineDao.read(lineId);
        if (oldLine == null) {
            return false;
        }
        return updateLine(lineId, oldLine.lineName(), newOperatorId);
    }

    public boolean updateLine(int lineId, String newLineName, int newOperatorId) {return LineDao.update(new Line(lineId, newLineName, newOperatorId));}

    public boolean deleteLine(int lineId) {return LineDao.delete(lineId);}

    public boolean addStation(String stationName) {return StationDao.create(new Station(-1, stationName));}

    public Station getStationInfo(int stationId) {return StationDao.read(stationId);}

    public List<Station> getAllStations() {return StationDao.readAll();}

    public boolean updateStation(int stationId, String newStationName) {return StationDao.update(new Station(stationId, newStationName));}

    public boolean deleteStation(int stationId) {return StationDao.delete(stationId);}

    public boolean addOperator(String operatorName) {return OperatorDao.create(new Operator(-1, operatorName));}

    public Operator getOperatorInfo(int operatorId) {return OperatorDao.read(operatorId);}

    public List<Operator> getAllOperators() {return OperatorDao.readAll();}

    public boolean updateOperator(int operatorId, String newOperatorName) {return OperatorDao.update(new Operator(operatorId, newOperatorName));}

    public boolean deleteOperator(int operatorId) {return OperatorDao.delete(operatorId);}

    public List<Line> getAllLinesByOperator(int operatorId) {return LineDao.allLinesOfOperator(operatorId);}

    public List<Line> getAllLinesByStation(int stationId) {return LineDao.allLinesAtStation(stationId);}

    public List<Station> getAllStationsByLine(int lineId) {return StationDao.allStationsOnLine(lineId);}

    public boolean deleteStop(int lineId, int stationId) {return LineDao.deleteStop(lineId, stationId);}

    public boolean addStop(int lineId, int stationId) {return LineDao.addStop(lineId, stationId);}
}
