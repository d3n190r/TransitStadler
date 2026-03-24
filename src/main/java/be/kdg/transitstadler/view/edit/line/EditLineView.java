package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.stringConverter.OperatorStringConverter;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.cellFactory.ListViewStationCellFactory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        lblLineId = new Label("Line Id");
        lblLineName = new Label("Line Name");
        lblOperatorId = new Label("Operator Id");
        lblOperatorName = new Label("Operator name");
        lblStops = new Label("Stops");

        tfLineId = new TextField();
        tfLineId.setDisable(true);
        tfLineName = new TextField();
        tfOperatorId = new TextField();
        tfOperatorId.setDisable(true);

        cbOperatorName = new ComboBox<>();
        cbOperatorName.setConverter(new OperatorStringConverter());

        lvStationList = new ListView<>();
        lvStationList.setCellFactory(new ListViewStationCellFactory());

        btnAddStop = new Button("Add stop");
        btnDeleteStop = new Button("Delete stop");
        btnCancel = new Button("Cancel");
        btnSave = new Button("Save");
        btnDelete = new Button("Delete");
    }

    private void layoutNodes() {
        hbId = new HBox(lblLineId, tfLineId);
        hbName = new HBox(lblLineName, tfLineName);
        hbOperatorId = new HBox(lblOperatorId, tfOperatorId);
        hbOperatorName = new HBox(lblOperatorName, cbOperatorName);
        hbButtons = new HBox(btnCancel, btnSave, btnDelete);
        hbStopButtons = new HBox(btnAddStop, btnDeleteStop);

        vbStops = new VBox(lblStops, lvStationList, hbStopButtons);
        vbCenter = new VBox(hbId, hbName, hbOperatorId, hbOperatorName, vbStops, hbButtons);

        this.setCenter(vbCenter);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(hbId, 2);
        hbId.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(hbName, 2);
        hbName.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(hbOperatorId, 2);
        hbOperatorId.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(hbOperatorName, 2);
        hbOperatorName.setAlignment(Pos.CENTER);

        LayoutUtils.applyMarginsToChildren(hbButtons, 7);

        LayoutUtils.applyMarginsToChildren(hbStopButtons, 7);

        LayoutUtils.applyMarginsToChildren(vbCenter, 5);
        LayoutUtils.applyMarginsToChildren(this, 5);

        btnSave.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), null)));
        btnSave.setTextFill(Color.WHITE);
        btnDelete.setTextFill(Color.WHITE);

        btnAddStop.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), null)));
        btnDeleteStop.setBackground(new Background(new BackgroundFill(Color.ORANGERED, new CornerRadii(5), null)));

        this.setPrefSize(300, 400);
    }

    public TextField getTfLineId() {
        return tfLineId;
    }

    public TextField getTfLineName() {
        return tfLineName;
    }

    public TextField getTfOperatorId() {
        return tfOperatorId;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnCancel() {return btnCancel;}

    public Button getBtnDelete() {return btnDelete;}

    public ComboBox<Operator> getCbOperatorName() {
        return cbOperatorName;
    }

    public ListView<Station> getLvStationList() {
        return lvStationList;
    }

    public Button getBtnAddStop() {return btnAddStop;}

    public Button getBtnDeleteStop() {return btnDeleteStop;}
}
