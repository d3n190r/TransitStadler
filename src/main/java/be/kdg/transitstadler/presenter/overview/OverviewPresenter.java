package be.kdg.transitstadler.presenter.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.presenter.PresenterUtils;
import be.kdg.transitstadler.presenter.create.CreateLinePresenter;
import be.kdg.transitstadler.view.create.CreateLineView;
import be.kdg.transitstadler.presenter.create.CreateOperatorPresenter;
import be.kdg.transitstadler.view.create.CreateOperatorView;
import be.kdg.transitstadler.presenter.create.CreateStationPresenter;
import be.kdg.transitstadler.view.create.CreateStationView;
import be.kdg.transitstadler.presenter.edit.EditLinePresenter;
import be.kdg.transitstadler.view.edit.EditLineView;
import be.kdg.transitstadler.presenter.edit.EditOperatorPresenter;
import be.kdg.transitstadler.view.edit.EditOperatorView;
import be.kdg.transitstadler.presenter.edit.EditStationPresenter;
import be.kdg.transitstadler.view.edit.EditStationView;
import be.kdg.transitstadler.view.overview.OverviewView;
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
                PresenterUtils.setupStage(createLineStage, createLineView, "Create Line");
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
                PresenterUtils.setupStage(createOperatorStage, createOperatorView, "Create Operator");
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
                PresenterUtils.setupStage(createStationStage, createStationView, "Create Station");
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
                PresenterUtils.setupStage(editLineStage, editLineView, "Edit Line");
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
                PresenterUtils.setupStage(editOperatorStage, editOperatorView, "Edit Operator");
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
                PresenterUtils.setupStage(editStationStage, editStationView, "Edit Station");
                editStationStage.showAndWait();
                updateView();
            }
        });
    }

    protected abstract void updateView();
}
