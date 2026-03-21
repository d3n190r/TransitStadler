package be.kdg.transitstadler.view.edit.line.addStop;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

public class AddStopPresenter {
    private final TransitStadlerModel model;
    private final AddStopView view;
    private final Line line;

    public AddStopPresenter(TransitStadlerModel model, AddStopView view, Line line) {
        this.model = model;
        this.view = view;
        this.line = line;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getCbStations().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Station> observableValue, Station oldValue, Station newValue) {
                model.addStop(line.lineId(), newValue.stationId());
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {
        List<Station> lineStationList = model.getAllStationsByLine(line.lineId());
        for (Station currentStation : model.getAllStations()) {
            if (!lineStationList.contains(currentStation)) {
                this.view.getCbStations().getItems().add(currentStation);
            }
        }
    }
}
