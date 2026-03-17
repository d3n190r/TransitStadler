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
     * Makes it easy to create the tables in the DB.
     */
    public static void buildDatabase() {
        Database.executeAny("DROP SCHEMA IF EXISTS public CASCADE", new Object[0]);
        Database.executeAny("CREATE SCHEMA public", new Object[0]);

        Database.executeAny("CREATE TABLE IF NOT EXISTS Operator (OperatorId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, OperatorName VARCHAR(255) UNIQUE NOT NULL);", new Object[0]);
        Database.executeAny("CREATE TABLE IF NOT EXISTS Station (StationId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, StationName VARCHAR(255) UNIQUE NOT NULL);", new Object[0]);
        Database.executeAny("CREATE TABLE IF NOT EXISTS Line (LineId INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, LineName VARCHAR(255) NOT NULL, OperatorId INTEGER REFERENCES Operator(OperatorId) NOT NULL, UNIQUE(LineName, OperatorId));", new Object[0]);
        Database.executeAny("CREATE TABLE IF NOT EXISTS Stop (LineId INTEGER REFERENCES Line(LineId), StationId INTEGER REFERENCES Station(StationId), StopSequence int NOT NULL, PRIMARY KEY(LineId, StationId), FOREIGN KEY (StationId) REFERENCES Station(StationId), FOREIGN KEY (LineId) REFERENCES Line(LineId), UNIQUE (LineId, StopSequence));", new Object[0]);
    }
}
