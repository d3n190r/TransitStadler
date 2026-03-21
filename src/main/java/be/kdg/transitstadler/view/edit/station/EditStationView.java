package be.kdg.transitstadler.view.edit.station;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditStationView extends VBox {
    private Label lblStationId;
    private Label lblStationName;

    private TextField tfStationId;
    private TextField tfStationName;

    private Button btnCancel;
    private Button btnSave;
    private Button btnDelete;

    private HBox hbButtons;

    public EditStationView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblStationId = new Label("Station Id");
        this.lblStationName = new Label("Station Name");

        this.btnCancel = new Button("Cancel");
        this.btnSave = new Button("Save");
        this.btnDelete = new Button("Delete");

        this.tfStationId = new TextField();
        this.tfStationName = new TextField();
        this.tfStationId.setDisable(true);
    }

    private void layoutNodes() {
        hbButtons = new HBox(btnCancel, btnSave, btnDelete);

        this.getChildren().addAll(this.lblStationId, this.tfStationId, this.lblStationName, this.tfStationName, hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this, 5);
    }

    public TextField getTfStationId() {
        return tfStationId;
    }

    public TextField getTfStationName() {
        return tfStationName;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }
}
