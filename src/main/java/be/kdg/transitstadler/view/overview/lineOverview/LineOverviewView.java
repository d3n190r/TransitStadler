package be.kdg.transitstadler.view.overview.lineOverview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.overview.OverviewView;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewLineCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import be.kdg.transitstadler.view.utils.LayoutUtils;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Igor Goossens (INF 101)
 */
public class LineOverviewView extends OverviewView {
    // private Node attributes (javafx.scene.control)
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
    private HBox hbLines;

    private VBox vbLineInfo;
    private VBox vbStations;

    public LineOverviewView() {
        super("General Overview");
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    /**
     * Creates all the required nodes for the view (this doesn't include the panes).
     */
    private void initialiseNodes() {
        // javafx.scene.control
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
        hbLines = new HBox();

        vbLineInfo = new VBox();
        vbStations = new VBox();

        // Add to Panes
        hbCenter.getChildren().addAll(vbStations, vbLineInfo);
        hbLines.getChildren().addAll(lblLines, lvLinesList);

        vbTop.getChildren().addAll(hbLines);
        vbLineInfo.getChildren().addAll(lblId, tfLineId, lblOperator, tfOperatorName, imgNetwork);
        vbStations.getChildren().addAll(lblStations, lvStationList);
    }

    /**
     * Changes the appearance of the nodes created in this.initialiseNodes() & this.layoutNodes().
     */
    protected void setNodeMarkup() {
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
        LayoutUtils.applyMarginsToChildren(hbLines, 5);
        LayoutUtils.applyMarginsToChildren(hbCenter, 5);
            // HBox
        hbLines.setAlignment(Pos.CENTER);
            // VBox
        vbStations.setAlignment(Pos.CENTER);
        vbLineInfo.setAlignment(Pos.TOP_CENTER);
            // OverviewView
        this.setPrefSize(600, 500);

        // ???
        VBox.setMargin(imgNetwork, new Insets(10, 0, 0, 0));

        miEditOperator.setDisable(true);
    }

    public ListView<Line> getLvLinesList() {return lvLinesList;}

    public ListView<Station> getLvStationList() {return lvStationList;}

    public TextField getTfLineId() {return tfLineId;}

    public TextField getTfOperatorName() {return tfOperatorName;}

    public ImageView getImgNetwork() {return imgNetwork;}

    @Override
    public Line getSelectedLine() {
        return lvLinesList.getSelectionModel().getSelectedItem();
    }

    @Override
    public Operator getSelectedOperator() {
        return null;
    }

    @Override
    public Station getSelectedStation() {
        return lvStationList.getSelectionModel().getSelectedItem();
    }
}
