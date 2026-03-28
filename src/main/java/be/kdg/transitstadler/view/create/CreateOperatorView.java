package be.kdg.transitstadler.view.create;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CreateOperatorView extends VBox {
    private Label lblOperatorName;
    private TextField txtOperatorName;
    private Button btnCancel;
    private Button btnCreateOperator;

    private HBox hbButtons;
    private HBox hbOperatorName;

    public CreateOperatorView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblOperatorName = new Label("Operator Name");
        this.txtOperatorName = new TextField();
        this.btnCancel = new Button("Cancel");
        this.btnCreateOperator = new Button("Create");
    }

    private void layoutNodes() {
        this.hbButtons = new HBox(this.btnCancel, this.btnCreateOperator);
        this.hbOperatorName = new HBox(this.lblOperatorName, this.txtOperatorName);

        this.getChildren().addAll(this.hbOperatorName, this.hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this.hbOperatorName, 5);
        LayoutUtils.applyMarginsToChildren(this.hbButtons, 5);
        this.hbButtons.setAlignment(Pos.TOP_CENTER);
        this.btnCreateOperator.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        this.btnCreateOperator.setTextFill(Color.WHITE);
        this.hbOperatorName.setAlignment(Pos.CENTER);
    }

    public Button getBtnCancel() {return this.btnCancel;}

    public Button getBtnCreateOperator() {return this.btnCreateOperator;}

    public TextField getTxtOperatorName() {return this.txtOperatorName;}
}
