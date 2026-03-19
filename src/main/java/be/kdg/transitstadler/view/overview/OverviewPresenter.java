package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewPresenter {
    private TransitStadlerModel model;
    private OverviewView view;

    String currentLine = null;
    String currentStation = null;

    /**
     * @param model The model that handles the data.
     * @param view The view that interacts with the user.
     */
    public OverviewPresenter(TransitStadlerModel model, OverviewView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    /**
     * Adds all the eventhandlers for the controls in the view.
     */
    private void addEventHandlers() {}

    /**
     * Puts the data from the model in the view.
     */
    private void updateView() {
        view.getLvStationList().getItems().clear();
        for (Station currentStation: model.getAllStations()) {
            view.getLvStationList().getItems().add(currentStation);
        }
        view.getLvLinesList().getItems().clear();
        for (Line currentLine: model.getAllLines()) {
            view.getLvLinesList().getItems().add(currentLine);
        }
    }
}
