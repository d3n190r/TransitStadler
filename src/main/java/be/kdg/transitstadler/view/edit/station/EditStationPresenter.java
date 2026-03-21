package be.kdg.transitstadler.view.edit.station;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Station;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.function.Consumer;

public class EditStationPresenter {
    private final TransitStadlerModel model;
    private final EditStationView view;
    private final Station station;

    public EditStationPresenter(TransitStadlerModel model, EditStationView view, Station station) {
        this.model = model;
        this.view = view;
        this.station = station;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnCancel().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getScene().getWindow().hide();
            }
        });

        this.view.getBtnSave().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.updateStation(station.stationId(), view.getTfStationName().getText());
                view.getScene().getWindow().hide();
            }
        });

        this.view.getBtnDelete().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Station");
                alert.setHeaderText("Do you really want to delete " + station.stationName() + "?");
                alert.showAndWait().ifPresent(new Consumer<>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                        if (buttonType == ButtonType.OK) {
                            model.deleteStation(station.stationId());
                            view.getScene().getWindow().hide();
                        }
                    }
                });
                model.deleteStation(station.stationId());
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {
        this.view.getTfStationId().setText(String.valueOf(station.stationId()));
        this.view.getTfStationName().setText(station.stationName());
    }
}
