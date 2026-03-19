package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

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
    private void addEventHandlers() {
        // When a new lines gets selected
        this.view.getLvLinesList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Line>() {
            @Override
            public void changed(ObservableValue<? extends Line> observableValue, Line oldValue, Line newValue) {
                List<Station> stationList = view.getLvStationList().getItems();
                stationList.clear();
                List<Station> newStationList = model.getAllStationsByLine(newValue.lineId());
                if (newStationList != null) {
                    for (Station currentStation : newStationList) {
                        view.getLvStationList().getItems().add(currentStation);
                    }
                    view.getTfLineId().setText(String.valueOf(newValue.lineId()));
                    view.getTfOperatorName().setText(model.getOperatorInfo(newValue.operatorId()).operatorName());
                }
                view.getBtnEditLine().setDisable(false);
                view.getBtnEditOperator().setDisable(false);
                view.getBtnEditStation().setDisable(true);
            }
        });

        // When a new station gets selected
        this.view.getLvStationList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Station>() {
            @Override
            public void changed(ObservableValue<? extends Station> observableValue, Station station, Station t1) {
                if (view.getBtnEditStation().isDisabled()) {
                    view.getBtnEditStation().setDisable(false);
                }
            }
        });
    }

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

        view.getLvLinesList().getSelectionModel().select(0);
    }
}
