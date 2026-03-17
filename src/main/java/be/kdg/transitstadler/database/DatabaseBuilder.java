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
     * @param dropEverything If true the whole schema will be dropped before rebuilding it.
     * @return A boolean indicating whether any query failed to execute.
     */
    public static boolean buildDatabase(boolean dropEverything) {
        boolean success = true;

        if (dropEverything) {
            success &= Database.executeAny("DROP SCHEMA IF EXISTS public CASCADE");
            success &= Database.executeAny("CREATE SCHEMA public");
        }

        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS operator (operatorId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, operatorName VARCHAR(255) UNIQUE NOT NULL);");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS station (stationId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, stationName VARCHAR(255) UNIQUE NOT NULL);");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS line (lineId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, lineName VARCHAR(255) NOT NULL, operatorId INTEGER REFERENCES operator(operatorId) NOT NULL, UNIQUE(lineName, operatorId));");
        success &= Database.executeAny("CREATE TABLE IF NOT EXISTS stop (lineId INTEGER REFERENCES line(lineId), stationId INTEGER REFERENCES station(stationId), stopSequence int NOT NULL, PRIMARY KEY(lineId, stationId), FOREIGN KEY (stationId) REFERENCES station(stationId), FOREIGN KEY (lineId) REFERENCES line(lineId), UNIQUE (lineId, stopSequence));");

        return success;
    }
}
