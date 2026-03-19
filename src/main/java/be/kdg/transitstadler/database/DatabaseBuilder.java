package be.kdg.transitstadler.database;

/**
 * @author Igor Goossens (INF 101)
 */
public class DatabaseBuilder {
    /**
     * static class -> private constructor
     */
    private DatabaseBuilder() {
        // TODO: error
        System.err.println("[DatabaseBuilder.DatabaseBuilder()] An instance of DatabaseBuilder was created.");
    }

    /**
     * Makes it easy to create the required tables in the database.
     * @param dropEverythingAndAddData If true all the tables will be dropped before rebuilding and data will be inserted afterwards.
     * @return A boolean indicating whether any query failed to execute.
     */
    public static boolean buildDatabase(boolean dropEverythingAndAddData) {
        boolean success = true;

        if (dropEverythingAndAddData) {
            success &= Database.executeAny("DROP TABLE IF EXISTS stop");
            success &= Database.executeAny("DROP TABLE IF EXISTS line");
            success &= Database.executeAny("DROP TABLE IF EXISTS station");
            success &= Database.executeAny("DROP TABLE IF EXISTS operator");
        }

        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS operator (operatorId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, operatorName VARCHAR(255) UNIQUE NOT NULL);");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS station (stationId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, stationName VARCHAR(255) UNIQUE NOT NULL);");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS line (lineId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, lineName VARCHAR(255) NOT NULL, operatorId INTEGER REFERENCES operator(operatorId), UNIQUE(lineName, operatorId));");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS stop (lineId INTEGER REFERENCES line(lineId), stationId INTEGER REFERENCES station(stationId), stopSequence int NOT NULL, PRIMARY KEY(lineId, stationId), UNIQUE (lineId, stopSequence));");

        if (dropEverythingAndAddData) {
            success &= Database.executeAny("INSERT INTO operator (operatorName) VALUES ('SNCB')");
            success &= Database.executeAny("INSERT INTO operator (operatorName) VALUES ('STIB')");
            success &= Database.executeAny("INSERT INTO operator (operatorName) VALUES ('LE TEC')");
            success &= Database.executeAny("INSERT INTO operator (operatorName) VALUES ('DE LIJN')");

            success &= Database.executeAny("INSERT INTO line (lineName, operatorId) VALUES ('M2', 1)");
            success &= Database.executeAny("INSERT INTO line (lineName, operatorId) VALUES ('M6', 1)");
            success &= Database.executeAny("INSERT INTO line (lineName, operatorId) VALUES ('T44', 1)");
            success &= Database.executeAny("INSERT INTO line (lineName, operatorId) VALUES ('T89', 1)");
            success &= Database.executeAny("INSERT INTO line (lineName, operatorId) VALUES ('S1', 0)");

            success &= Database.executeAny("INSERT INTO station (stationName) VALUES ('Delacroix')");
            success &= Database.executeAny("INSERT INTO station (stationName) VALUES ('Gare du Midi')");
            success &= Database.executeAny("INSERT INTO station (stationName) VALUES ('Schaerbeek Gare')");

            success &= Database.executeAny("INSERT INTO stop (lineId, stationId, stopSequence) VALUES (0, 1, 1)");
            success &= Database.executeAny("INSERT INTO stop (lineId, stationId, stopSequence) VALUES (0, 0, 2)");
            success &= Database.executeAny("INSERT INTO stop (lineId, stationId, stopSequence) VALUES (1, 1, 1)");
            success &= Database.executeAny("INSERT INTO stop (lineId, stationId, stopSequence) VALUES (3, 2, 1)");
            success &= Database.executeAny("INSERT INTO stop (lineId, stationId, stopSequence) VALUES (4, 1, 3)");
        }

        return success;
    }
}
