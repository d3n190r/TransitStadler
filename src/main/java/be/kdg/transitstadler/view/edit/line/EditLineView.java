package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.StationCellFactory;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
        vbCenter = new VBox();
        vbCenter.getChildren().addAll(lblLineId, tfLineId, lblLineName, tfLineName, lblOperatorId, tfOperatorId, lblOperatorName, mbOperatorName, lblStops, lvStationList, btnSave);

        this.setCenter(vbCenter);
    }

    private void setNodeMarkup() {
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
}
