package be.kdg.transitstadler.view.edit.line;

import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.StationCellFactory;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class EditLineView extends VBox {
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

        mbOperatorName = new MenuButton("Edit");

        lvStationList = new ListView<Station>();
        lvStationList.setCellFactory(new StationCellFactory());

        btnSave = new Button("Save");
    }

    private void layoutNodes() {
        this.getChildren().addAll(lblLineId, tfLineId, lblLineName, tfLineName, lblOperatorId, tfOperatorId, lblOperatorName, mbOperatorName, lblStops, lvStationList, btnSave);
    }

    private void setNodeMarkup() {}

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
}
