package be.kdg.transitstadler.view.overview.lineOverview;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;

import be.kdg.transitstadler.view.overview.OverviewPresenter;
import be.kdg.transitstadler.view.overview.generalOverview.GeneralOverviewPresenter;
import be.kdg.transitstadler.view.overview.generalOverview.GeneralOverviewView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.util.List;

/**
 * @author Igor Goossens (INF 101)
 */
public class LineOverviewPresenter extends OverviewPresenter {
    private final LineOverviewView view;

    /**
     * @param model The model that handles the data.
     * @param view The view that interacts with the user.
     */
    public LineOverviewPresenter(TransitStadlerModel model, LineOverviewView view) {
        super(model, view);
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
                    view.getMiEditLine().setDisable(true);
                    view.getMiEditOperator().setDisable(true);
                    return;
                } else {
                    List<Station> stationList = view.getLvStationList().getItems();
                    stationList.clear();
                    List<Station> newStationList = model.getAllStationsByLine(newValue.lineId());
                    if (newStationList != null) {
                        stationList.addAll(newStationList);
                    }
                    view.getTfLineId().setText(String.valueOf(newValue.lineId()));
                    view.getTfOperatorName().setText(model.getOperatorInfo(newValue.operatorId()).operatorName());
                    view.getMiEditLine().setDisable(false);
                    view.getMiEditStation().setDisable(true);
                }
            }
        });

        // When a new station gets selected
        this.view.getLvStationList().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Station> observableValue, Station station, Station t1) {
                view.getMiEditStation().setDisable(false);
            }
        });

        // When toUnassociated gets clicked
        this.view.getBtnSwitchOverview().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GeneralOverviewView generalOverviewView = new GeneralOverviewView();
                new GeneralOverviewPresenter(model, generalOverviewView);
                view.getScene().setRoot(generalOverviewView);
                generalOverviewView.getScene().getWindow().sizeToScene();
            }
        });

        this.view.getImgNetwork().setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                dragEvent.acceptTransferModes(TransferMode.COPY);
            }
        });

        this.view.getImgNetwork().setOnDragDropped(new EventHandler<>() {
            @Override
            public void handle(DragEvent mouseEvent) {
                Dragboard dragboard = mouseEvent.getDragboard();
                if (dragboard.hasFiles()) {
                    File file = dragboard.getFiles().getFirst();
                    Image image = new Image(file.toURI().toString());
                    view.getImgNetwork().setImage(image);
                    view.getScene().getWindow().sizeToScene();
                }
            }
        });
    }

    /**
     * Puts the data from the model in the view.
     */
    @Override
    protected void updateView() {
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
