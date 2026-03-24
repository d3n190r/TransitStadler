package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewLineCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import be.kdg.transitstadler.view.utils.LayoutUtils;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewView extends BorderPane {
    // private Node attributes (javafx.scene.control)
    private Button btnAddLine;
    private Button btnAddOperator;
    private Button btnAddStation;
    private Button btnEditLine;
    private Button btnEditOperator;
    private Button btnEditStation;
    private Button btnUnassociated;

    private Label lblId;
    private Label lblLines;
    private Label lblOperator;
    private Label lblStations;

    private ListView<Line> lvLinesList;
    private ListView<Station> lvStationList;

    private TextField tfLineId;
    private TextField tfOperatorName;

    // private Node attributes (javafx.scene.image)
    private ImageView imgNetwork;

    // private Node attributes (javafx.scene.layout)
    private HBox hbCenter;
    private HBox hbEditButtons;
    private HBox hbInsertButtons;
    private HBox hbHeader;

    private VBox vbFooter;
    private VBox vbLineInfo;
    private VBox vbStations;

    public OverviewView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    /**
     * Creates all the required nodes for the view (this doesn't include the panes).
     */
    private void initialiseNodes() {
        // javafx.scene.control
        btnAddLine = new Button("Add Line");
        btnAddOperator = new Button("Add Operator");
        btnAddStation = new Button("Add Station");
        btnEditLine = new Button("Edit Line");
        btnEditOperator = new Button("Edit Operator");
        btnEditStation = new Button("Edit Station");
        btnUnassociated = new Button("Unassociated elements");

        lblId = new Label("Id:");
        lblLines = new Label("Lines:");
        lblOperator = new Label("Operator:");
        lblStations = new Label("Stations:");

        lvLinesList = new ListView<>();
        lvStationList = new ListView<>();

        tfLineId = new TextField();
        tfOperatorName = new TextField();

        // javafx.scene.image
        imgNetwork = new ImageView(new Image("networkMap.png"));
    }

    /**
     * Creates the panes and adds the nodes from this.initialiseNodes() to them.
     */
    private void layoutNodes() {
        // javafx.scene.layout
        hbCenter = new HBox();
        hbEditButtons = new HBox();
        hbInsertButtons = new HBox();
        hbHeader = new HBox();

        vbFooter = new VBox();
        vbLineInfo = new VBox();
        vbStations = new VBox();

        // Add to Panes
        hbCenter.getChildren().addAll(vbStations, vbLineInfo);
        hbEditButtons.getChildren().addAll(btnEditLine, btnEditStation, btnEditOperator, btnUnassociated);
        hbInsertButtons.getChildren().addAll(btnAddLine, btnAddStation, btnAddOperator);
        hbHeader.getChildren().addAll(lblLines, lvLinesList);

        vbFooter.getChildren().addAll(hbEditButtons, hbInsertButtons);
        vbLineInfo.getChildren().addAll(lblId, tfLineId, lblOperator, tfOperatorName, imgNetwork);
        vbStations.getChildren().addAll(lblStations, lvStationList);

        // Add to OverviewView
        this.setBottom(vbFooter);
        this.setCenter(hbCenter);
        this.setTop(hbHeader);
    }

    /**
     * Changes the appearance of the nodes created in this.initialiseNodes() & this.layoutNodes().
     */
    private void setNodeMarkup() {
        // btn
        // lbl
        lblLines.setFont(Font.font("System", FontWeight.BOLD, 12));
        lblLines.setAlignment(Pos.CENTER_LEFT);
        lblLines.setMinHeight(40);
        lblLines.setMaxHeight(40);
        lblLines.setPadding(new Insets(0, 0, 5, 0));
        // lv
        lvLinesList.setOrientation(Orientation.HORIZONTAL);
        lvLinesList.setPrefWidth(300);
        lvLinesList.setMinHeight(40);
        lvLinesList.setMaxHeight(40);
        lvLinesList.setCellFactory(new ListViewLineCellFactory());
        lvStationList.setCellFactory(new ListViewStationCellFactory());
        // tf
        tfLineId.setDisable(true);
        tfOperatorName.setDisable(true);
        // imageview
        imgNetwork.setPreserveRatio(true);
        imgNetwork.setFitWidth(300);
        // Panes
        LayoutUtils.applyMarginsToChildren(this, 5);
        LayoutUtils.applyMarginsToChildren(hbHeader, 5);
        LayoutUtils.applyMarginsToChildren(hbCenter, 5);
        LayoutUtils.applyMarginsToChildren(hbEditButtons, 5);
        LayoutUtils.applyMarginsToChildren(hbInsertButtons, 5);
            // HBox
        hbHeader.setMinHeight(40);
        hbHeader.setMaxHeight(40);
        hbInsertButtons.setAlignment(Pos.TOP_CENTER);
        hbEditButtons.setAlignment(Pos.TOP_CENTER);
        hbHeader.setAlignment(Pos.CENTER);
        hbCenter.setAlignment(Pos.CENTER);
            // VBox
        vbFooter.setAlignment(Pos.CENTER);
        vbStations.setAlignment(Pos.CENTER);
        vbLineInfo.setAlignment(Pos.TOP_CENTER);
            // OverviewView
        this.setPrefSize(600, 500);

        // ???
        VBox.setMargin(imgNetwork, new Insets(10, 0, 0, 0));
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
