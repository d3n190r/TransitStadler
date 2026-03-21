package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.LineCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.StationCellFactory;
import be.kdg.transitstadler.view.utils.LayoutUtils;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewView extends BorderPane {
    // private Node attributes (javafx.scene.control)
    private ListView<Line> lvLinesList;
    private ListView<Station> lvStationList;

    private Label lblLines;
    private Label lblStations;
    private Label lblOperator;
    private Label lblId;

    private Button btnEditLine;
    private Button btnEditStation;
    private Button btnEditOperator;
    private Button btnUnassociated;
    private Button btnAddLine;
    private Button btnAddStation;
    private Button btnAddOperator;

    private TextField tfLineId;
    private TextField tfOperatorName;

    private ImageView imgNetwork;

    // private Node attributes (javafx.scene.layout)
    private HBox hbHeader;
    private HBox hbCenter;
    private HBox hbEditButtons;
    private HBox hbInsertButtons;

    private VBox vbFooter;
    private VBox vbStations;
    private VBox vbLineInfo;

    public OverviewView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    /**
     * Creates all the required nodes for the view (this doesn't include the panes).
     */
    private void initialiseNodes() {
        // ListView //
        lvLinesList = new ListView<>();
        lvLinesList.setCellFactory(new LineCellFactory());


        lvStationList = new ListView<>();
        lvStationList.setCellFactory(new StationCellFactory());

        // Label //
        lblLines = new Label("Lines:");
        lblStations = new Label("Stations:");
        lblOperator = new Label("Operator:");
        lblId = new Label("Id:");

        // Button //
        btnEditStation = new Button("Edit Station");
        btnEditLine = new Button("Edit Line");
        btnEditOperator = new Button("Edit Operator");
        btnUnassociated = new Button("Unassociated elements");
        btnAddLine = new Button("Add Line");
        btnAddStation = new Button("Add Station");
        btnAddOperator = new Button("Add Operator");

        // TextField //
        tfLineId = new TextField();
        tfOperatorName = new TextField();

        // ImageView //
        imgNetwork = new ImageView(new Image("networkMap.png"));
    }

    /**
     * Creates the panes and adds the nodes from this.initialiseNodes() to them.
     */
    private void layoutNodes() {
        // Panes //
        hbHeader = new HBox();
        hbCenter = new HBox();
        hbEditButtons = new HBox();
        hbInsertButtons = new HBox();

        vbFooter = new VBox();
        vbLineInfo = new VBox();
        vbStations = new VBox();

        // Add to Panes //
        hbHeader.getChildren().addAll(lblLines, lvLinesList);
        hbCenter.getChildren().addAll(vbStations, vbLineInfo);
        hbEditButtons.getChildren().addAll(btnEditLine, btnEditStation, btnEditOperator, btnUnassociated);
        hbInsertButtons.getChildren().addAll(btnAddLine, btnAddStation, btnAddOperator);

        vbFooter.getChildren().addAll(hbEditButtons, hbInsertButtons);
        vbStations.getChildren().addAll(lblStations, lvStationList);
        vbLineInfo.getChildren().addAll(lblId, tfLineId, lblOperator, tfOperatorName, imgNetwork);

        this.setTop(hbHeader);
        this.setCenter(hbCenter);
        this.setBottom(vbFooter);
    }

    /**
     * Changes the appearance of the nodes created in this.initialiseNodes() & this.layoutNodes().
     */
    private void setNodeMarkup() {
        // Set visual space between Nodes //
        LayoutUtils.applyMarginsToChildren(this, 5);
        LayoutUtils.applyMarginsToChildren(hbHeader, 5);
        LayoutUtils.applyMarginsToChildren(hbCenter, 5);
        LayoutUtils.applyMarginsToChildren(hbEditButtons, 5);
        LayoutUtils.applyMarginsToChildren(hbInsertButtons, 5);

        // Location of Nodes //
        hbHeader.setAlignment(Pos.CENTER);
        hbCenter.setAlignment(Pos.CENTER);
        vbFooter.setAlignment(Pos.CENTER);

        vbStations.setAlignment(Pos.CENTER);
        vbLineInfo.setAlignment(Pos.TOP_CENTER);

        // Some improvements to the Lines (right) part of the screen //
        lblLines.setStyle("-fx-font-weight: bold");
        lblLines.setPadding(new Insets(0, 0, 5, 0));

        lvLinesList.setOrientation(Orientation.HORIZONTAL);
        lvLinesList.setMaxHeight(40);

        tfLineId.setDisable(true);
        tfOperatorName.setDisable(true);

        imgNetwork.setPreserveRatio(true);
        imgNetwork.setFitWidth(300);
        VBox.setMargin(imgNetwork, new Insets(10, 0, 0, 0));

        this.setPrefSize(600, 500);
    }

    // TODO: Documentation
    public ListView<Line> getLvLinesList() {
        return lvLinesList;
    }

    // TODO: Documentation
    public ListView<Station> getLvStationList() {
        return lvStationList;
    }

    public Button getBtnEditStation() {return btnEditStation;}

    public Button getBtnEditLine() {return btnEditLine;}

    public Button getBtnEditOperator() {return btnEditOperator;}

    public TextField getTfLineId() {return tfLineId;}

    public TextField getTfOperatorName() {return tfOperatorName;}

    public Button getBtnUnassociated() {return btnUnassociated;}

    public ImageView getImgNetwork() {return imgNetwork;}

    public Button getBtnAddLine() {return btnAddLine;}

    public Button getBtnAddStation() {return btnAddStation;}

    public Button getBtnAddOperator() {return btnAddOperator;}
}
