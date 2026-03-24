package be.kdg.transitstadler.view.unassociatedElements;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewOperatorCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class UnassociatedElementsView extends VBox {
    private Label lblStations;
    private Label lblOperators;

    private ListView<Station> lvStations;
    private ListView<Operator> lvOperator;

    private Button btnEditStation;
    private Button btnEditOperator;
    private Button btnBackToLineOverview;

    private VBox vbStations;
    private VBox vbOperator;

    private HBox hbLists;

    public UnassociatedElementsView() {
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

        btnEditStation = new Button("Edit station");
        btnEditOperator = new Button("Edit operator");
        btnBackToLineOverview = new Button("Back to line overview");
    }

    private void layoutNodes() {
        vbStations = new VBox(lblStations, lvStations, btnEditStation);
        vbOperator = new VBox(lblOperators, lvOperator, btnEditOperator);

        hbLists = new HBox(vbStations, vbOperator);

        this.getChildren().setAll(hbLists, btnBackToLineOverview);
    }

    private void setNodeMarkup() {
        vbStations.setAlignment(Pos.TOP_CENTER);
        vbOperator.setAlignment(Pos.TOP_CENTER);

        HBox.setHgrow(vbStations, Priority.ALWAYS);
        HBox.setHgrow(vbOperator, Priority.ALWAYS);

        this.setAlignment(Pos.TOP_CENTER);
        this.setFillWidth(true);
        this.setPrefSize(600, 500);
    }

    public Button getBtnEditStation() {
        return btnEditStation;
    }

    public Button getBtnEditOperator() {
        return btnEditOperator;
    }

    public Button getBtnBackToLineOverview() {
        return btnBackToLineOverview;
    }

    public ListView<Station> getLvStations() {
        return lvStations;
    }

    public ListView<Operator> getLvOperator() {
        return lvOperator;
    }
}
