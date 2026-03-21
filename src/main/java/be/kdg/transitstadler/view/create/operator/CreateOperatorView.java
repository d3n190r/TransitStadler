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
        lblOperatorName = new Label("Operator Name");
        txtOperatorName = new TextField();
        btnCreateOperator = new Button("Create");
    }

    private void layoutNodes() {
        this.getChildren().addAll(lblOperatorName, txtOperatorName, btnCreateOperator);
    }

    private void setNodeMarkup() {}

    public Button getBtnCreateOperator() {
        return btnCreateOperator;
    }

    public TextField getTxtOperatorName() {
        return txtOperatorName;
    }
}
