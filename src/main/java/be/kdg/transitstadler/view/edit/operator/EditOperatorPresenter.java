package be.kdg.transitstadler.view.edit.operator;

import be.kdg.transitstadler.model.TransitStadlerModel;

public class EditOperatorPresenter {
    private TransitStadlerModel model;
    private EditOperatorView view;

    public EditOperatorPresenter(TransitStadlerModel model, EditOperatorView view) {
        this.model = model;
        this.view = view;
    }
}
