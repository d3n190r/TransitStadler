package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.stringConverter.OperatorStringConverter;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EditLineView extends BorderPane {
    private Label lblLineId;
    private Label lblLineName;
    private Label lblOperatorId;
    private Label lblOperatorName;
    private Label lblStops;

    private TextField tfLineId;
    private TextField tfLineName;
    private TextField tfOperatorId;

    private ComboBox<Operator> cbOperatorName;

    private ListView<Station> lvStationList;

    private Button btnCancel;
    private Button btnSave;
    private Button btnDelete;
    private Button btnAddStop;
    private Button btnDeleteStop;

    private VBox vbCenter;
    private VBox vbStops;

    private HBox hbId;
    private HBox hbName;
    private HBox hbOperatorId;
    private HBox hbOperatorName;
    private HBox hbButtons;
    private HBox hbStopButtons;

    public EditLineView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblLineId = new Label("Line Id");
        this.lblLineName = new Label("Line Name");
        this.lblOperatorId = new Label("Operator Id");
        this.lblOperatorName = new Label("Operator name");
        this.lblStops = new Label("Stops");

        this.tfLineId = new TextField();
        this.tfLineId.setDisable(true);
        this.tfLineName = new TextField();
        this.tfOperatorId = new TextField();
        this.tfOperatorId.setDisable(true);

        this.cbOperatorName = new ComboBox<>();
        this.cbOperatorName.setConverter(new OperatorStringConverter());

        this.lvStationList = new ListView<>();
        this.lvStationList.setCellFactory(new ListViewStationCellFactory());

        this.btnAddStop = new Button("Add stop");
        this.btnDeleteStop = new Button("Delete stop");
        this.btnCancel = new Button("Cancel");
        this.btnSave = new Button("Save");
        this.btnDelete = new Button("Delete");
    }

    private void layoutNodes() {
        this.hbId = new HBox(this.lblLineId, this.tfLineId);
        this.hbName = new HBox(this.lblLineName, this.tfLineName);
        this.hbOperatorId = new HBox(this.lblOperatorId, this.tfOperatorId);
        this.hbOperatorName = new HBox(this.lblOperatorName, this.cbOperatorName);
        this.hbButtons = new HBox(this.btnCancel, this.btnSave, this.btnDelete);
        this.hbStopButtons = new HBox(this.btnAddStop, this.btnDeleteStop);

        this.vbStops = new VBox(this.lblStops, this.lvStationList, this.hbStopButtons);
        this.vbCenter = new VBox(this.hbId, this.hbName, this.hbOperatorId, this.hbOperatorName, this.vbStops, this.hbButtons);

        this.setCenter(this.vbCenter);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this.hbId, 2);
        this.hbId.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(this.hbName, 2);
        this.hbName.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(this.hbOperatorId, 2);
        this.hbOperatorId.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(this.hbOperatorName, 2);
        this.hbOperatorName.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(this.hbButtons, 7);

        LayoutUtils.applyMarginsToChildren(this.hbStopButtons, 7);

        LayoutUtils.applyMarginsToChildren(this.vbCenter, 5);
        LayoutUtils.applyMarginsToChildren(this, 5);

        this.btnSave.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        this.btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), null)));
        this.btnSave.setTextFill(Color.WHITE);
        this.btnDelete.setTextFill(Color.WHITE);

        this.btnAddStop.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), null)));
        this.btnDeleteStop.setBackground(new Background(new BackgroundFill(Color.ORANGERED, new CornerRadii(5), null)));

        this.setPrefSize(300, 400);
    }

    public TextField getTfLineId() {return this.tfLineId;}

    public TextField getTfLineName() {return this.tfLineName;}

    public TextField getTfOperatorId() {return this.tfOperatorId;}

    public Button getBtnSave() {return this.btnSave;}

    public Button getBtnCancel() {return this.btnCancel;}

    public Button getBtnDelete() {return this.btnDelete;}

    public ComboBox<Operator> getCbOperatorName() {return this.cbOperatorName;}

    public ListView<Station> getLvStationList() {return this.lvStationList;}

    public Button getBtnAddStop() {return this.btnAddStop;}

    public Button getBtnDeleteStop() {return this.btnDeleteStop;}
}
