package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.StationCellFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
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

    private MenuButton mbOperatorName;

    private ListView<Station> lvStationList;

    private Button btnSave;

    private VBox vbCenter;

    private HBox hbId;
    private HBox hbName;
    private HBox hbOperatorId;
    private HBox hbOperatorName;

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

        mbOperatorName = new MenuButton();

        lvStationList = new ListView<>();
        lvStationList.setCellFactory(new StationCellFactory());

        btnSave = new Button("Save");
    }

    private void layoutNodes() {
        hbId = new HBox();
        hbId.getChildren().addAll(lblLineId, tfLineId);

        hbName = new HBox();
        hbName.getChildren().addAll(lblLineName, tfLineName);

        hbOperatorId = new HBox();
        hbOperatorId.getChildren().addAll(lblOperatorId, tfOperatorId);

        hbOperatorName = new HBox();
        hbOperatorName.getChildren().addAll(lblOperatorName, mbOperatorName);

        vbCenter = new VBox();
        vbCenter.getChildren().addAll(hbId, hbName, hbOperatorId, hbOperatorName, lblStops, lvStationList, btnSave);

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

    public MenuButton getMbOperatorName() {
        return mbOperatorName;
    }

    public ListView<Station> getLvStationList() {
        return lvStationList;
    }
}
