module be.kdg.transitstadler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens be.kdg.transitstadler to javafx.fxml;
    exports be.kdg.transitstadler;
}