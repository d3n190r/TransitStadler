package be.kdg.transitstadler.database.dao;

import be.kdg.transitstadler.database.Database;
import be.kdg.transitstadler.model.businessobject.Operator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorDao {
    private static final String operatorTableName = "operator";

    private OperatorDao() {System.err.println("[OperatorDao.OperatoDao()] An instance of OperatorDao was created.");}

    private static List<Operator> convertDbResultSetToObjectList(ResultSet dbSet) {
        if (dbSet == null) {return null;}
        ArrayList<Operator> result = new ArrayList<>();
        try {
            dbSet.beforeFirst();
            while (dbSet.next()) {
                result.add(new Operator(dbSet.getInt("operatorId"), dbSet.getString("operatorName")));
            }
        } catch (SQLException e) {
            System.err.println("[OperatorDao.convertDbResultSetToObjectList()] Could not convert result from database.");
            return null;
        }
        return result;
    }

    public static boolean create(Operator newOperator) {
        Object[] parameterValues = new Object[] {newOperator.operatorName()};
        if (Database.executeAny("INSERT INTO " + operatorTableName + "(operatorName) VALUES (?)", parameterValues)) {
            return true;
        } else {
            System.err.println("[OperatorDao.create()] Could not create new operator.");
            return false;
        }
    }

    public static Operator read(int operatorId) {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + operatorTableName + " WHERE operatorId = ?", new Object[] {operatorId});
        List<Operator> result = convertDbResultSetToObjectList(resultRows);
        if (result == null) {
            System.err.println("[OperatorDao.read()] Could not find operator in the database width id " + operatorId);
            return null;
        } else if (result.size() != 1) {
            System.err.println("[OperatorDao.read()] Found multiple operators in the database with id " + operatorId);
        }
        return result.getFirst();
    }

    public static List<Operator> readAll() {
        ResultSet resultRows = Database.executeQuery("SELECT * FROM " + operatorTableName);
        return convertDbResultSetToObjectList(resultRows);
    }

    public static boolean update(Operator updatedOperator) {
        Object[] parameterValues = new Object[] {updatedOperator.operatorName(), updatedOperator.operatorId()};
        int changes = Database.executeChange("UPDATE " + operatorTableName + " SET operatorName = ? WHERE operatorId = ?", parameterValues);
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[OperatorDao.update()] No changes were made in the database.");
        } else {
            System.err.println("[OperatorDao.update()] Somehow multiple operators with the same id exist in the database.");
        }
        return false;
    }

    public static boolean delete(int operatorId) {
        int changes = Database.executeChange("DELETE FROM " + operatorTableName + " WHERE operatorId = ?", new Object[] {operatorId});
        if (changes == 1) {
            return true;
        } else if (changes < 1) {
            System.err.println("[OperatoDao.delete()] No changes were made in the database.");
        } else {
            System.err.println("[OperatorDao.delete()] Somehow multiple operators with the same id exist in the database.");
        }
        return false;
    }
}
