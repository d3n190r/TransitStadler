package be.kdg.transitstadler.view.create.station;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Station;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class CreateStationPresenter {
    private final TransitStadlerModel model;
    private final CreateStationView view;

    public CreateStationPresenter(TransitStadlerModel model, CreateStationView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.addStation(view.getTxtStationName().getText());
                view.getScene().getWindow().hide();
            }
        });

        view.getTxtStationName().setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String text = view.getTxtStationName().getText();
                if (text.isEmpty()) {
                    view.getBtnCreate().setDisable(true);
                    return;
                }
                for (Station currentStation : model.getAllStations()) {
                    if (currentStation.stationName().equals(text)) {
                        view.getBtnCreate().setDisable(true);
                        return;
                    }
                }
                view.getBtnCreate().setDisable(false);
            }
        });
    }

    private void updateView() {
        view.getBtnCreate().setDisable(true);
    }
}
