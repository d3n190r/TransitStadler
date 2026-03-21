package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.create.line.CreateLinePresenter;
import be.kdg.transitstadler.view.create.line.CreateLineView;
import be.kdg.transitstadler.view.create.operator.CreateOperatorPresenter;
import be.kdg.transitstadler.view.create.operator.CreateOperatorView;
import be.kdg.transitstadler.view.create.station.CreateStationPresenter;
import be.kdg.transitstadler.view.create.station.CreateStationView;
import be.kdg.transitstadler.view.edit.line.EditLinePresenter;
import be.kdg.transitstadler.view.edit.line.EditLineView;
import be.kdg.transitstadler.view.edit.operator.EditOperatorPresenter;
import be.kdg.transitstadler.view.edit.operator.EditOperatorView;
import be.kdg.transitstadler.view.edit.station.EditStationPresenter;
import be.kdg.transitstadler.view.edit.station.EditStationView;

import be.kdg.transitstadler.view.unassociatedElements.UnassociatedElementsPresenter;
import be.kdg.transitstadler.view.unassociatedElements.UnassociatedElementsView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.DragEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewPresenter {
    private final TransitStadlerModel model;
    private final OverviewView view;

    /**
     * @param model The model that handles the data.
     * @param view The view that interacts with the user.
     */
    public OverviewPresenter(TransitStadlerModel model, OverviewView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    /**
     * Adds all the eventhandlers for the controls in the view.
     */
    private void addEventHandlers() {
        // When a new lines gets selected
        this.view.getLvLinesList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Line> observableValue, Line oldValue, Line newValue) {
                if (newValue == null) {
                    view.getBtnEditLine().setDisable(true);
                    view.getBtnEditStation().setDisable(true);
                    view.getBtnEditOperator().setDisable(true);
                    return;
                }
                List<Station> stationList = view.getLvStationList().getItems();
                stationList.clear();
                List<Station> newStationList = model.getAllStationsByLine(newValue.lineId());
                if (newStationList != null) {
                    for (Station currentStation : newStationList) {
                        view.getLvStationList().getItems().add(currentStation);
                    }
                    view.getTfLineId().setText(String.valueOf(newValue.lineId()));
                    view.getTfOperatorName().setText(model.getOperatorInfo(newValue.operatorId()).operatorName());
                }
                view.getBtnEditLine().setDisable(false);
                view.getBtnEditOperator().setDisable(false);
                view.getBtnEditStation().setDisable(true);
            }
        });

        // When a new station gets selected
        this.view.getLvStationList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Station> observableValue, Station station, Station t1) {
                if (view.getBtnEditStation().isDisabled()) {
                    view.getBtnEditStation().setDisable(false);
                }
            }
        });

        // When editLine gets clicked
        this.view.getBtnEditLine().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditLineView editLineView = new EditLineView();
                new EditLinePresenter(model, editLineView, view.getLvLinesList().getSelectionModel().getSelectedItem());
                Stage editLineStage = new Stage();
                editLineStage.setTitle("Edit Line");
                editLineStage.initOwner(view.getScene().getWindow());
                editLineStage.initModality(Modality.APPLICATION_MODAL);
                editLineStage.setScene(new Scene(editLineView));
                editLineStage.showAndWait();
                updateView();
            }
        });

        // When editOperator gets clicked
        this.view.getBtnEditOperator().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditOperatorView editOperatorView = new EditOperatorView();
                new EditOperatorPresenter(model, editOperatorView, model.getOperatorInfo(view.getLvLinesList().getSelectionModel().getSelectedItem().operatorId()));
                Stage editOperatorStage = new Stage();
                editOperatorStage.setTitle("Edit Operator");
                editOperatorStage.initOwner(view.getScene().getWindow());
                editOperatorStage.initModality(Modality.APPLICATION_MODAL);
                editOperatorStage.setScene(new Scene(editOperatorView));
                editOperatorStage.showAndWait();
                updateView();
            }
        });

        // When editStation gets clicked
        this.view.getBtnEditStation().setOnAction(new  EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditStationView editStationView = new EditStationView();
                new EditStationPresenter(model, editStationView, view.getLvStationList().getSelectionModel().getSelectedItem());
                Stage editStationStage = new Stage();
                editStationStage.setTitle("Edit Station");
                editStationStage.initOwner(view.getScene().getWindow());
                editStationStage.initModality(Modality.APPLICATION_MODAL);
                editStationStage.setScene(new Scene(editStationView));
                editStationStage.showAndWait();
                updateView();
            }
        });

        // When toUnassociated gets clicked
        this.view.getBtnUnassociated().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                UnassociatedElementsView unassociatedElementsView = new UnassociatedElementsView();
                new UnassociatedElementsPresenter(model, unassociatedElementsView);
                view.getScene().setRoot(unassociatedElementsView);
                unassociatedElementsView.getScene().getWindow().sizeToScene();
            }
        });

        this.view.getImgNetwork().setOnDragExited(new EventHandler<>() {
            @Override
            public void handle(DragEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.showAndWait();
            }
        });

        this.view.getBtnAddLine().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateLineView createLineView = new CreateLineView();
                new CreateLinePresenter(model, createLineView);
                Stage createLineStage = new Stage();
                createLineStage.setTitle("Create Line");
                createLineStage.initOwner(view.getScene().getWindow());
                createLineStage.initModality(Modality.APPLICATION_MODAL);
                createLineStage.setScene(new Scene(createLineView));
                createLineStage.showAndWait();
                updateView();
            }
        });

        this.view.getBtnAddOperator().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateOperatorView createOperatorView = new CreateOperatorView();
                new CreateOperatorPresenter(model, createOperatorView);
                Stage createOperatorStage = new Stage();
                createOperatorStage.setTitle("Create Operator");
                createOperatorStage.initOwner(view.getScene().getWindow());
                createOperatorStage.initModality(Modality.APPLICATION_MODAL);
                createOperatorStage.setScene(new Scene(createOperatorView));
                createOperatorStage.showAndWait();
                updateView();
            }
        });

        this.view.getBtnAddStation().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateStationView createStationView = new CreateStationView();
                new CreateStationPresenter(model, createStationView);
                Stage createStationStage = new Stage();
                createStationStage.setTitle("Create Station");
                createStationStage.initOwner(view.getScene().getWindow());
                createStationStage.initModality(Modality.APPLICATION_MODAL);
                createStationStage.setScene(new Scene(createStationView));
                createStationStage.showAndWait();
                updateView();
            }
        });
    }

    /**
     * Puts the data from the model in the view.
     */
    private void updateView() {
        view.getLvStationList().getItems().clear();
        for (Station currentStation: model.getAllStations()) {
            view.getLvStationList().getItems().add(currentStation);
        }
        view.getLvLinesList().getItems().clear();
        for (Line currentLine: model.getAllLines()) {
            view.getLvLinesList().getItems().add(currentLine);
        }

        view.getLvLinesList().getSelectionModel().select(0);
    }
}
