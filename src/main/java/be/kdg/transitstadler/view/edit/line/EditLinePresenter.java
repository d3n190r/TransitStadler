package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;

import be.kdg.transitstadler.model.businessobject.Station;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

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

        this.view.getBtnCancel().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getScene().getWindow().hide();
            }
        });

        this.view.getBtnDelete().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete line");
                alert.setHeaderText("Do you really want to delte " + line.lineName()  + "?");
                alert.showAndWait().ifPresent(new Consumer<>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                        if (buttonType == ButtonType.OK) {
                            model.deleteLine(line.lineId());
                            view.getScene().getWindow().hide();
                        }
                    }
                });
            }
        });

        this.view.getCbOperatorName().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Operator> observableValue, Operator oldValue, Operator newValue) {
                view.getTfOperatorId().setText(String.valueOf(newValue.operatorId()));
            }
        });
    }

    private void updateView() {
        this.view.getTfLineName().setText(line.lineName());
        this.view.getTfLineId().setText(String.valueOf(line.lineId()));
        this.view.getTfOperatorId().setText(String.valueOf(line.operatorId()));
        this.view.getCbOperatorName().getItems().clear();
        for (Operator operator : model.getAllOperators()) {
            this.view.getCbOperatorName().getItems().add(operator);
        }

        for (Station station : model.getAllStationsByLine(line.lineId())) {
            this.view.getLvStationList().getItems().add(station);
        }
    }
}
