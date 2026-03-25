package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewLineCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewView extends BorderPane {
    // private Node attributes (javafx.scene.control)
    private Button btnUnassociated;

    private Label lblId;
    private Label lblLines;
    private Label lblOperator;
    private Label lblStations;

    private ListView<Line> lvLinesList;
    private ListView<Station> lvStationList;

    private Menu menuEdit;
    private Menu menuAdd;

    private MenuBar menuBar;

    private MenuItem miAddLine;
    private MenuItem miAddOperator;
    private MenuItem miAddStation;
    private MenuItem miEditLine;
    private MenuItem miEditOperator;
    private MenuItem miEditStation;

    private TextField tfLineId;
    private TextField tfOperatorName;

    // private Node attributes (javafx.scene.image)
    private ImageView imgNetwork;

    // private Node attributes (javafx.scene.layout)
    private HBox hbCenter;
    private HBox hbLines;
    private HBox hbFooter;

    private VBox vbHeader;
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
        btnUnassociated = new Button("Unassociated elements");

        lblId = new Label("Id:");
        lblLines = new Label("Lines:");
        lblOperator = new Label("Operator:");
        lblStations = new Label("Stations:");

        lvLinesList = new ListView<>();
        lvStationList = new ListView<>();

        miAddLine = new MenuItem("Add Line");
        miAddOperator = new MenuItem("Add Operator");
        miAddStation = new MenuItem("Add Station");
        miEditLine = new MenuItem("Edit LIne");
        miEditOperator = new MenuItem("Edit Operator");
        miEditStation = new MenuItem("Edit Station");

        menuEdit = new Menu("Edit");
        menuAdd = new Menu("Add");
        menuEdit.getItems().addAll(miEditLine, miEditOperator, miEditStation);
        menuAdd.getItems().addAll(miAddLine, miAddOperator, miAddStation);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuEdit, menuAdd);

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
        hbLines = new HBox();
        hbFooter = new HBox();

        vbHeader = new VBox();
        vbLineInfo = new VBox();
        vbStations = new VBox();

        // Add to Panes
        hbCenter.getChildren().addAll(vbStations, vbLineInfo);
        hbLines.getChildren().addAll(lblLines, lvLinesList);
        hbFooter.getChildren().addAll(btnUnassociated);

        vbHeader.getChildren().addAll(menuBar, hbLines);
        vbLineInfo.getChildren().addAll(lblId, tfLineId, lblOperator, tfOperatorName, imgNetwork);
        vbStations.getChildren().addAll(lblStations, lvStationList);

        // Add to OverviewView
        this.setBottom(hbFooter);
        this.setCenter(hbCenter);
        this.setTop(vbHeader);
    }

    /**
     * Changes the appearance of the nodes created in this.initialiseNodes() & this.layoutNodes().
     */
    private void setNodeMarkup() {
        // btn
        btnUnassociated.setMinWidth(150);
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
        // mb
        menuBar.setMinWidth(300);
        // tf
        tfLineId.setDisable(true);
        tfOperatorName.setDisable(true);
        // imageview
        imgNetwork.setPreserveRatio(true);
        imgNetwork.setFitWidth(300);
        // Panes
        LayoutUtils.applyMarginsToChildren(this, 5);
        LayoutUtils.applyMarginsToChildren(hbLines, 5);
        LayoutUtils.applyMarginsToChildren(hbCenter, 5);
            // HBox
        hbLines.setAlignment(Pos.CENTER);
        hbCenter.setAlignment(Pos.CENTER);
            // VBox
        hbFooter.setAlignment(Pos.TOP_CENTER);
        vbStations.setAlignment(Pos.CENTER);
        vbLineInfo.setAlignment(Pos.TOP_CENTER);
        vbHeader.setAlignment(Pos.TOP_CENTER);
            // OverviewView
        this.setPrefSize(600, 500);

        // ???
        VBox.setMargin(imgNetwork, new Insets(10, 0, 0, 0));
    }

    public ListView<Line> getLvLinesList() {return lvLinesList;}

    public ListView<Station> getLvStationList() {return lvStationList;}

    public TextField getTfLineId() {return tfLineId;}

    public TextField getTfOperatorName() {return tfOperatorName;}

    public Button getBtnUnassociated() {return btnUnassociated;}

    public ImageView getImgNetwork() {return imgNetwork;}

    public MenuItem getMiEditLine() {return miEditLine;}

    public MenuItem getMiEditOperator() {return miEditOperator;}

    public MenuItem getMiEditStation() {return miEditStation;}

    public MenuItem getMiAddLine() {return miAddLine;}

    public MenuItem getMiAddOperator() {return miAddOperator;}

    public MenuItem getMiAddStation() {return miAddStation;}
}
