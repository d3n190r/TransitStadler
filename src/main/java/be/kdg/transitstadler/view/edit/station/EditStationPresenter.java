package be.kdg.transitstadler.view.edit.station;

import be.kdg.transitstadler.model.TransitStadlerModel;

public class EditStationPresenter {
    private final TransitStadlerModel model;
    private final EditStationView view;

    public EditStationPresenter(TransitStadlerModel model, EditStationView view) {
        this.model = model;
        this.view = view;
    }
}
