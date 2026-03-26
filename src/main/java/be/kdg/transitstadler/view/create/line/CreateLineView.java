package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.view.utils.stringConverter.OperatorStringConverter;
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
        this.lblLineName = new Label("Line Name");
        this.txtLineName = new TextField();
        this.lblOperatorId = new Label("Operator ID");
        this.cbOperatorId = new ComboBox<>();
        this.cbOperatorId.setConverter(new OperatorStringConverter());
        this.btnCreate = new Button("Create");
    }

    private void layoutNodes() {
        this.hbLineName = new HBox(this.lblLineName, this.txtLineName);
        this.hbOperatorId = new HBox(this.lblOperatorId, this.cbOperatorId);
        this.getChildren().addAll(this.hbLineName, this.hbOperatorId, this.btnCreate);
    }

    private void setNodeMarkup() {}

    public Button getBtnCreate() {return this.btnCreate;}

    public TextField getTxtLineName() {return this.txtLineName;}

    public ComboBox<Operator> getCbOperatorId() {return this.cbOperatorId;}
}
