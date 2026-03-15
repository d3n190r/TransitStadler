package be.kdg.transitstadler;

import be.kdg.transitstadler.model.TransitStadlerModel;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Igor Goossens (INF 101)
 */
public class Main  extends Application {
    @Override
    public void start(Stage primaryStage) {
        TransitStadlerModel model = new TransitStadlerModel();
        primaryStage.setTitle("TransitStadler");
        primaryStage.show();
    }
}
