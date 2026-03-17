package be.kdg.transitstadler.database;

import java.sql.SQLException;

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

        Database.executeAny("CREATE TABLE Operator (OperatorName varchar(255) PRIMARY KEY)", new Object[0]);
        Database.executeAny("CREATE TABLE Station (StationName varchar(255) PRIMARY KEY)", new Object[0]);
        Database.executeAny("CREATE TABLE Line (LineName varchar(255), OperatorName varchar(255), PRIMARY KEY (LineName, OperatorName), FOREIGN KEY (OperatorName) REFERENCES Operator(OperatorName))", new Object[0]);
        Database.executeAny("CREATE TABLE Stop (StationName varchar(255), LineName varchar(255), OperatorName varchar(255), StopSequence int NOT NULL, PRIMARY KEY(StationName, LineName, OperatorName), FOREIGN KEY (StationName) REFERENCES Station(StationName), FOREIGN KEY (LineName, OperatorName) REFERENCES Line(LineName, OperatorName), UNIQUE (LineName, StopSequence))", new Object[0]);
    }
}
