package be.kdg.transitstadler.presenter.create;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.create.CreateStationView;
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
        this.view.getBtnCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.addStation(view.getTxtStationName().getText());
                view.getScene().getWindow().hide();
            }
        });

        this.view.getTxtStationName().setOnKeyTyped(new EventHandler<KeyEvent>() {
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

        this.view.getBtnCancel().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {this.view.getBtnCreate().setDisable(true);}
}
