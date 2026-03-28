package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewOperatorCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GeneralOverviewView extends OverviewView {
    private Label lblStations;
    private Label lblOperators;

    private ListView<Station> lvStations;
    private ListView<Operator> lvOperator;

    private VBox vbStations;
    private VBox vbOperator;

    public GeneralOverviewView() {
        super("Line Overview");
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblStations = new Label("Stations");
        this.lblOperators = new Label("Operators");

        this.lvStations = new ListView<>();
        this.lvStations.setCellFactory(new ListViewStationCellFactory());

        this.lvOperator = new ListView<>();
        lvOperator.setCellFactory(new ListViewOperatorCellFactory());
    }

    private void layoutNodes() {
        vbStations = new VBox(lblStations, lvStations);
        vbOperator = new VBox(lblOperators, lvOperator);

        this.hbCenter.getChildren().addAll(vbStations, vbOperator);
    }

    private void setNodeMarkup() {
        vbStations.setAlignment(Pos.TOP_CENTER);
        vbOperator.setAlignment(Pos.TOP_CENTER);

        HBox.setHgrow(vbStations, Priority.ALWAYS);
        HBox.setHgrow(vbOperator, Priority.ALWAYS);

        this.lblOperators.setFont(Font.font("System", FontWeight.BOLD, 12));
        this.lblStations.setFont(Font.font("System", FontWeight.BOLD, 12));

        this.lvStations.setPrefWidth(290);
        this.lvOperator.setPrefWidth(290);

        this.setPrefSize(600, 500);

        this.miEditLine.setDisable(true);

        LayoutUtils.applyMarginsToChildren(this.hbCenter, 5);
    }

    public ListView<Station> getLvStations() {return lvStations;}

    public ListView<Operator> getLvOperator() {return lvOperator;}

    @Override
    public Line getSelectedLine() {
        return null;
    }

    @Override
    public Operator getSelectedOperator() {
        return lvOperator.getSelectionModel().getSelectedItem();
    }

    @Override
    public Station getSelectedStation() {
        return lvStations.getSelectionModel().getSelectedItem();
    }
}
