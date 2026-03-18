package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.edit.EditPresenter;
import be.kdg.transitstadler.view.edit.EditTypes;
import be.kdg.transitstadler.view.edit.EditView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewPresenter {
    private TransitStadlerModel model;
    private OverviewView view;

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
        this.view.getBtnEditStation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openEditWindow(EditTypes.STATION);
            }
        });

        this.view.getBtnEditLine().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openEditWindow(EditTypes.LINE);
            }
        });

        this.view.getBtnEditBoth().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                openEditWindow(EditTypes.STOP);
            }
        });
    }

    /**
     * Puts the data from the model in the view.
     */
    private void updateView() {
        view.getLvStationList().getItems().clear();
        for (Station currentStation: model.getAllStations()) {
            view.getLvStationList().getItems().add(currentStation.stationName());
        }
        view.getLvLinesList().getItems().clear();
        for (Line currentLine: model.getAllLines()) {
            view.getLvLinesList().getItems().add(currentLine.lineName());
        }
    }

    /**
     * Helper function to open the editWindow.
     */
    private void openEditWindow(EditTypes editType) {
        EditView editView = new EditView(editType);
        new EditPresenter(model, editView);
        Stage editStage = new Stage();
        editStage.initOwner(view.getScene().getWindow());
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.setScene(new Scene(editView));
        editStage.setMinWidth(500);
        editStage.showAndWait();
    }
}
