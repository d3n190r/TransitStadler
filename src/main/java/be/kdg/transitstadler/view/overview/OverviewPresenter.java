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

    // TODO: documentation
    public OverviewPresenter(TransitStadlerModel model, OverviewView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    // TODO: Documentation
    private void addEventHandlers() {
        // TODO: Implement
    }

    // TODO: Documentation
    private void updateView() {
        for (Station currentStation: model.getAllStations()) {
            view.getLvStationList().getItems().add(currentStation.stationName());
        }
        for (Line currentLine: model.getAllLines()) {
            view.getLvLinesList().getItems().add(currentLine.lineName());
        }
    }
}
