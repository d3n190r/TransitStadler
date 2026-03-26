package be.kdg.transitstadler.view.edit.station;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
        this.hbButtons = new HBox(this.btnCancel, this.btnSave, this.btnDelete);

        this.getChildren().addAll(this.lblStationId, this.tfStationId, this.lblStationName, this.tfStationName, hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this, 5);
        LayoutUtils.applyMarginsToChildren(this.hbButtons, 5);
        this.hbButtons.setAlignment(Pos.TOP_CENTER);
        this.btnSave.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        this.btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), null)));
        this.btnSave.setFont(new Font("FreeSerif", 12));
        this.btnSave.setTextFill(Color.WHITE);
        this.btnDelete.setTextFill(Color.WHITE);
    }

    public TextField getTfStationId() {return this.tfStationId;}

    public TextField getTfStationName() {return this.tfStationName;}

    public Button getBtnCancel() {return this.btnCancel;}

    public Button getBtnSave() {return this.btnSave;}

    public Button getBtnDelete() {return this.btnDelete;}
}
