package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.TransitStadlerModel;

public class EditLinePresenter {
    private TransitStadlerModel model;
    private EditLineView view;

    public EditLinePresenter(TransitStadlerModel model, EditLineView view) {
        this.model = model;
        this.view = view;
    }
}
