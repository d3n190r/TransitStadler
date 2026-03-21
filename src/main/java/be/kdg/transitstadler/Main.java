package be.kdg.transitstadler;

import be.kdg.transitstadler.database.DatabaseBuilder;
import be.kdg.transitstadler.database.dao.LineDao;
import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.view.overview.OverviewPresenter;
import be.kdg.transitstadler.view.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Igor Goossens (INF 101)
 */
public class Main  extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseBuilder.buildDatabase(true);
        TransitStadlerModel model = new TransitStadlerModel();
        OverviewView view = new OverviewView();
        OverviewPresenter presenter = new OverviewPresenter(model, view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setTitle("TransitStadler");
        primaryStage.show();
    }
}
