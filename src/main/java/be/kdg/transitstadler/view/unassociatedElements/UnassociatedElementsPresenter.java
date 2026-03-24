package be.kdg.transitstadler.view.unassociatedElements;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.edit.operator.EditOperatorPresenter;
import be.kdg.transitstadler.view.edit.operator.EditOperatorView;
import be.kdg.transitstadler.view.edit.station.EditStationPresenter;
import be.kdg.transitstadler.view.edit.station.EditStationView;
import be.kdg.transitstadler.view.overview.OverviewPresenter;
import be.kdg.transitstadler.view.overview.OverviewView;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UnassociatedElementsPresenter {
    private final TransitStadlerModel model;
    private final UnassociatedElementsView view;

    public UnassociatedElementsPresenter(TransitStadlerModel model, UnassociatedElementsView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnEditOperator().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditOperatorView editOperatorView = new EditOperatorView();
                new EditOperatorPresenter(model, editOperatorView, model.getOperatorInfo(view.getLvOperator().getSelectionModel().getSelectedItem().operatorId()));
                Stage editOperatorStage = new Stage();
                editOperatorStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(editOperatorStage, editOperatorView, "Edit Operator");
                editOperatorStage.showAndWait();
                updateView();
            }
        });

        this.view.getBtnEditStation().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditStationView editStationView = new EditStationView();
                new EditStationPresenter(model, editStationView, view.getLvStations().getSelectionModel().getSelectedItem());
                Stage editStationStage = new Stage();
                editStationStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(editStationStage, editStationView, "Edit Station");
                editStationStage.showAndWait();
                updateView();
            }
        });

        this.view.getBtnBackToLineOverview().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OverviewView overviewView = new OverviewView();
                new OverviewPresenter(model, overviewView);
                view.getScene().setRoot(overviewView);
                overviewView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
        view.getLvOperator().getItems().clear();
        for (Operator currentOperator: model.getAllOperators()) {
            view.getLvOperator().getItems().add(currentOperator);
        }
        view.getLvOperator().getSelectionModel().select(0);

        view.getLvStations().getItems().clear();
        for (Station currentStation: model.getAllStations()) {
            view.getLvStations().getItems().add(currentStation);
        }
        view.getLvStations().getSelectionModel().select(0);
    }
}
