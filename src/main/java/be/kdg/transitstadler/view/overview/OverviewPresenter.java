package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
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
import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class OverviewPresenter {
    protected final TransitStadlerModel model;
    // view can't be used by children as they require a more specific type
    private final OverviewView view;

    protected OverviewPresenter(TransitStadlerModel model, OverviewView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        this.view.getMiAddLine().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateLineView createLineView = new CreateLineView();
                new CreateLinePresenter(model, createLineView);
                Stage createLineStage = new Stage();
                createLineStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(createLineStage, createLineView, "Create Line");
                createLineStage.showAndWait();
                updateView();
            }
        });

        this.view.getMiAddOperator().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateOperatorView createOperatorView = new CreateOperatorView();
                new CreateOperatorPresenter(model, createOperatorView);
                Stage createOperatorStage = new Stage();
                createOperatorStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(createOperatorStage, createOperatorView, "Create Operator");
                createOperatorStage.showAndWait();
                updateView();
            }
        });

        this.view.getMiAddStation().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateStationView createStationView = new CreateStationView();
                new CreateStationPresenter(model, createStationView);
                Stage createStationStage = new Stage();
                createStationStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(createStationStage, createStationView, "Create Station");
                createStationStage.showAndWait();
                updateView();
            }
        });

        this.view.getMiEditLine().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditLineView editLineView = new EditLineView();
                new EditLinePresenter(model, editLineView, view.getSelectedLine());
                Stage editLineStage = new Stage();
                editLineStage.initOwner(view.getScene().getWindow());
                editLineStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(editLineStage, editLineView, "Edit Line");
                editLineStage.showAndWait();
                updateView();
            }
        });

        this.view.getMiEditOperator().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditOperatorView editOperatorView = new EditOperatorView();
                new EditOperatorPresenter(model, editOperatorView, view.getSelectedOperator());
                Stage editOperatorStage = new Stage();
                editOperatorStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(editOperatorStage, editOperatorView, "Edit Operator");
                editOperatorStage.showAndWait();
                updateView();
            }
        });

        this.view.getMiEditStation().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditStationView editStationView = new EditStationView();
                new EditStationPresenter(model, editStationView, view.getSelectedStation());
                Stage editStationStage = new Stage();
                editStationStage.initModality(Modality.APPLICATION_MODAL);
                LayoutUtils.setupStage(editStationStage, editStationView, "Edit Station");
                editStationStage.showAndWait();
                updateView();
            }
        });
    }

    protected abstract void updateView();
}
