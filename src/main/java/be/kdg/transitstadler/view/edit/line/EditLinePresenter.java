package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;

public class EditLinePresenter {
    private TransitStadlerModel model;
    private EditLineView view;
    private Line line;

    public EditLinePresenter(TransitStadlerModel model, EditLineView view, Line line) {
        this.model = model;
        this.view = view;
        this.line = line;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {}

    private void updateView() {
        this.view.getTfLineName().setText(line.lineName());
        this.view.getTfLineId().setText(String.valueOf(line.lineId()));
        this.view.getTfOperatorId().setText(String.valueOf(line.operatorId()));
    }
}
