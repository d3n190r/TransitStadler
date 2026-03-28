package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewLineCellFactory;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import be.kdg.transitstadler.view.utils.stringConverter.OperatorStringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LineOverviewView extends OverviewView {
    private ComboBox<Operator> cbOperatorName;

    private Label lblId;
    private Label lblLines;
    private Label lblOperator;
    private Label lblStations;

    private ListView<Line> lvLinesList;
    private ListView<Station> lvStationList;

    private TextField tfLineId;

    private ImageView imgNetwork;

    private HBox hbLines;

    private VBox vbLineInfo;
    private VBox vbStations;

    public LineOverviewView() {
        super("General Overview");
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.cbOperatorName = new ComboBox<>();

        this.lblId = new Label("Id:");
        this.lblLines = new Label("Lines:");
        this.lblOperator = new Label("Operator:");
        this.lblStations = new Label("Stations:");

        this.lvLinesList = new ListView<>();
        this.lvStationList = new ListView<>();

        this.tfLineId = new TextField();

        this.imgNetwork = new ImageView(new Image("networkMap.png"));
    }

    private void layoutNodes() {
        this.hbLines = new HBox();

        this.vbLineInfo = new VBox();
        this.vbStations = new VBox();

        this.hbCenter.getChildren().addAll(this.vbStations, this.vbLineInfo);
        this.hbLines.getChildren().addAll(this.lblLines, this.lvLinesList);

        this.vbTop.getChildren().addAll(this.hbLines);
        this.vbLineInfo.getChildren().addAll(this.lblId, this.tfLineId, this.lblOperator, this.cbOperatorName, this.imgNetwork);
        this.vbStations.getChildren().addAll(this.lblStations, this.lvStationList);
    }

    protected void setNodeMarkup() {
        this.cbOperatorName.setConverter(new OperatorStringConverter());
        this.cbOperatorName.setDisable(true);
        this.cbOperatorName.setMinWidth(300);

        this.lblLines.setFont(Font.font("System", FontWeight.BOLD, 12));
        this.lblLines.setAlignment(Pos.CENTER_LEFT);
        this.lblLines.setMinHeight(40);
        this.lblLines.setMaxHeight(40);

        this.lvLinesList.setOrientation(Orientation.HORIZONTAL);
        this.lvLinesList.setPrefWidth(300);
        this.lvLinesList.setMinHeight(40);
        this.lvLinesList.setMaxHeight(40);
        this.lvLinesList.setCellFactory(new ListViewLineCellFactory());
        this.lvStationList.setCellFactory(new ListViewStationCellFactory());

        this.tfLineId.setDisable(true);

        this.imgNetwork.setPreserveRatio(true);
        this.imgNetwork.setFitWidth(300);

        LayoutUtils.applyMarginsToChildren(this.hbLines, 5);
        LayoutUtils.applyMarginsToChildren(this.hbCenter, 5);

        this.hbLines.setAlignment(Pos.CENTER);

        this.vbStations.setAlignment(Pos.CENTER);
        this.vbLineInfo.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(this.imgNetwork, new Insets(10, 0, 0, 0));
    }

    public ComboBox<Operator> getCbOperatorName() {return this.cbOperatorName;}

    public ListView<Line> getLvLinesList() {return this.lvLinesList;}

    public ListView<Station> getLvStationList() {return this.lvStationList;}

    public TextField getTfLineId() {return this.tfLineId;}

    public ImageView getImgNetwork() {return this.imgNetwork;}

    @Override
    public Line getSelectedLine() {return this.lvLinesList.getSelectionModel().getSelectedItem();}

    @Override
    public Operator getSelectedOperator() {return cbOperatorName.getValue();}

    @Override
    public Station getSelectedStation() {return lvStationList.getSelectionModel().getSelectedItem();}
}
