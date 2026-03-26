package be.kdg.transitstadler.view.create.station;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CreateStationView extends HBox {
    private Label lblStationName;
    private TextField txtStationName;
    private Button btnCreate;

    public CreateStationView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblStationName = new Label("Station Name");
        this.txtStationName = new TextField();
        this.btnCreate = new Button("Create");
    }

    private void layoutNodes() {this.getChildren().addAll(this.lblStationName, this.txtStationName, this.btnCreate);}

    private void setNodeMarkup() {}

    public TextField getTxtStationName() {return this.txtStationName;}

    public Button getBtnCreate() {return this.btnCreate;}
}
