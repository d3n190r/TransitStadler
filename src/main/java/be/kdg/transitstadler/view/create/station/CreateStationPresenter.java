package be.kdg.transitstadler.view.create.station;

import be.kdg.transitstadler.model.TransitStadlerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
    }

    private void updateView() {}
}
