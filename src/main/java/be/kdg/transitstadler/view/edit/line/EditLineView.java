package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.cellFactory.ComboBoxOperatorCellFactory;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.cellFactory.StationCellFactory;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        cbOperatorName.setConverter(new ComboBoxOperatorCellFactory());

        lvStationList = new ListView<>();
        lvStationList.setCellFactory(new StationCellFactory());

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
}
