package be.kdg.transitstadler.view.edit.operator;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EditOperatorView extends VBox {
    private Label lblOperatorId;
    private Label lblOperatorName;

    private TextField tfOperatorId;
    private TextField tfOperatorName;

    private Button btnCancel;
    private Button btnSave;
    private Button btnDelete;

    private HBox hbButtons;

    public EditOperatorView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.lblOperatorId = new Label("Operator Id");
        this.lblOperatorName = new Label("Operator Name");

        this.tfOperatorId = new TextField();
        this.tfOperatorId.setDisable(true);
        this.tfOperatorName = new TextField();

        this.btnCancel = new Button("Cancel");
        this.btnSave = new Button("Save");
        this.btnDelete = new Button("Delete");
    }

    private void layoutNodes() {
        this.hbButtons = new HBox(this.btnCancel, this.btnSave, this.btnDelete);

        this.getChildren().addAll(this.lblOperatorId, this.tfOperatorId, this.lblOperatorName, this.tfOperatorName, this.hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this.hbButtons, 7);
        LayoutUtils.applyMarginsToChildren(this, 5);
        this.btnSave.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        this.btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), null)));
        this.btnSave.setTextFill(Color.WHITE);
        this.btnDelete.setTextFill(Color.WHITE);
    }

    public Button getBtnCancel() {
        return this.btnCancel;
    }

    public Button getBtnSave() {
        return this.btnSave;
    }

    public Button getBtnDelete() {
        return this.btnDelete;
    }

    public TextField getTfOperatorName() {
        return this.tfOperatorName;
    }

    public TextField getTfOperatorId() {
        return this.tfOperatorId;
    }
}
