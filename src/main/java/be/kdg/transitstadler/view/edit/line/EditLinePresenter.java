package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class EditLinePresenter {
    private final TransitStadlerModel model;
    private final EditLineView view;
    private final Line line;

    public EditLinePresenter(TransitStadlerModel model, EditLineView view, Line line) {
        this.model = model;
        this.view = view;
        this.line = line;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnSave().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.updateLine(line.lineId(), view.getTfLineName().getText(), Integer.parseInt(view.getTfOperatorId().getText()));
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {
        this.view.getTfLineName().setText(line.lineName());
        this.view.getTfLineId().setText(String.valueOf(line.lineId()));
        this.view.getTfOperatorId().setText(String.valueOf(line.operatorId()));
        this.view.getMbOperatorName().setText(model.getOperatorInfo(line.operatorId()).operatorName());
        this.view.getMbOperatorName().getItems().clear();
        for (Operator operator : model.getAllOperators()) {
            this.view.getMbOperatorName().getItems().add(new MenuItem(operator.operatorName()));
        }
    }
}
