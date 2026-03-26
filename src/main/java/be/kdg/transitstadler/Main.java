package be.kdg.transitstadler;

import be.kdg.transitstadler.database.DatabaseBuilder;
import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.view.overview.lineOverview.LineOverviewPresenter;
import be.kdg.transitstadler.view.overview.lineOverview.LineOverviewView;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main  extends Application {
    @Override
    public void start(Stage primaryStage) {
        DatabaseBuilder.buildDatabase(false);
        TransitStadlerModel model = new TransitStadlerModel();
        LineOverviewView view = new LineOverviewView();
        new LineOverviewPresenter(model, view);
        LayoutUtils.setupStage(primaryStage, view, "TransitStadler");
        primaryStage.show();
    }
}
