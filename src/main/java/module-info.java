module be.kdg.transitstadler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens be.kdg.transitstadler to javafx.fxml;
    exports be.kdg.transitstadler;
    exports be.kdg.transitstadler.presenter;
    opens be.kdg.transitstadler.presenter to javafx.fxml;
}