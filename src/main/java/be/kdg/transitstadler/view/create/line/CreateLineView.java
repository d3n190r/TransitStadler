package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.view.utils.cellFactory.ComboBoxOperatorCellFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateLineView extends VBox {
    private Label lblLineName;
    private TextField txtLineName;
    private Label lblOperatorId;
    private ComboBox<Operator> cbOperatorId;
    private Button btnCreate;

    HBox hbOperatorId;
    HBox hbLineName;

    public CreateLineView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        lblLineName = new Label("Line Name");
        txtLineName = new TextField();
        lblOperatorId = new Label("Operator ID");
        cbOperatorId = new ComboBox<>();
        cbOperatorId.setConverter(new ComboBoxOperatorCellFactory());
        btnCreate = new Button("Create");
    }

    private void layoutNodes() {
        hbLineName = new HBox(lblLineName, txtLineName);
        hbOperatorId = new HBox(lblOperatorId, cbOperatorId);
        this.getChildren().addAll(hbLineName, hbOperatorId, btnCreate);
    }

    private void setNodeMarkup() {}

    public Button getBtnCreate() {
        return btnCreate;
    }

    public TextField getTxtLineName() {
        return txtLineName;
    }

    public ComboBox<Operator> getCbOperatorId() {
        return cbOperatorId;
    }
}
