module be.kdg.transitstadler {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.kdg.transitstadler to javafx.fxml;
    exports be.kdg.transitstadler;
}