package be.kdg.transitstadler;

import be.kdg.transitstadler.database.DatabaseBuilder;
import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.view.overview.OverviewPresenter;
import be.kdg.transitstadler.view.overview.OverviewView;
import be.kdg.transitstadler.view.utils.LayoutUtils;
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
        new OverviewPresenter(model, view);
        LayoutUtils.setupStage(primaryStage, view, "TransitStadler");
        primaryStage.show();
    }
}
