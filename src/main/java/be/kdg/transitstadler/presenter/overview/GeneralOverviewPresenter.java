package be.kdg.transitstadler.presenter.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.overview.GeneralOverviewView;
import be.kdg.transitstadler.view.overview.LineOverviewView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GeneralOverviewPresenter extends OverviewPresenter {
    private final GeneralOverviewView view;

    public GeneralOverviewPresenter(TransitStadlerModel model, GeneralOverviewView view) {
        super(model, view);
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnSwitchOverview().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LineOverviewView lineOverviewView = new LineOverviewView();
                new LineOverviewPresenter(model, lineOverviewView);
                view.getScene().setRoot(lineOverviewView);
                lineOverviewView.getScene().getWindow().sizeToScene();
            }
        });
    }

    @Override
    protected void updateView() {
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
