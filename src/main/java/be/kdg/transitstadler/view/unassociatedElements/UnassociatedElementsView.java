package be.kdg.transitstadler.view.unassociatedElements;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewOperatorCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.StationCellFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UnassociatedElementsView extends HBox {
    private Label lblStations;
    private Label lblOperators;

    private ListView<Station> lvStations;
    private ListView<Operator> lvOperator;

    private Button btnEditStation;
    private Button btnEditOperator;

    private VBox vbStations;
    private VBox vbOperator;

    public UnassociatedElementsView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblStations = new Label("Stations");
        this.lblOperators = new Label("Operators");

        this.lvStations = new ListView<>();
        this.lvStations.setCellFactory(new StationCellFactory());

        this.lvOperator = new ListView<>();
        lvOperator.setCellFactory(new ListViewOperatorCellFactory());

        btnEditStation = new Button("Edit station");
        btnEditOperator = new Button("Edit operator");
    }

    private void layoutNodes() {
        vbStations = new VBox(lblStations, lvStations, btnEditStation);
        vbOperator = new VBox(lblOperators, lvOperator, btnEditOperator);

        this.getChildren().setAll(vbStations, vbOperator);
    }

    private void setNodeMarkup() {
        vbStations.setAlignment(Pos.TOP_CENTER);
        vbOperator.setAlignment(Pos.TOP_CENTER);
    }
}
