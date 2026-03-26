package be.kdg.transitstadler.view.create.operator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CreateOperatorView extends HBox {
    private Label lblOperatorName;
    private TextField txtOperatorName;
    private Button btnCreateOperator;

    public CreateOperatorView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblOperatorName = new Label("Operator Name");
        this.txtOperatorName = new TextField();
        this.btnCreateOperator = new Button("Create");
    }

    private void layoutNodes() {this.getChildren().addAll(this.lblOperatorName, this.txtOperatorName, this.btnCreateOperator);}

    private void setNodeMarkup() {}

    public Button getBtnCreateOperator() {return this.btnCreateOperator;}

    public TextField getTxtOperatorName() {return this.txtOperatorName;}
}
