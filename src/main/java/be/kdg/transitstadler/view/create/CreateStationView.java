package be.kdg.transitstadler.view.create;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CreateStationView extends VBox {
    private Label lblStationName;
    private TextField txtStationName;
    private Button btnCancel;
    private Button btnCreate;

    private HBox hbLineName;
    private HBox hbButtons;

    public CreateStationView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblStationName = new Label("Station Name");
        this.txtStationName = new TextField();
        this.btnCancel = new Button("Cancel");
        this.btnCreate = new Button("Create");
    }

    private void layoutNodes() {
        this.hbLineName = new HBox(this.lblStationName, this.txtStationName);
        this.hbButtons = new HBox(this.btnCancel, this.btnCreate);

        this.getChildren().addAll(this.hbLineName, this.hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(hbLineName, 5);
        LayoutUtils.applyMarginsToChildren(hbButtons, 5);
        this.hbButtons.setAlignment(Pos.CENTER);
        this.btnCreate.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        this.btnCreate.setTextFill(Color.WHITE);
    }

    public TextField getTxtStationName() {return this.txtStationName;}

    public Button getBtnCancel() {return this.btnCancel;}

    public Button getBtnCreate() {return this.btnCreate;}
}
