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
        lblOperatorId = new Label("Operator Id");
        lblOperatorName = new Label("Operator Name");

        tfOperatorId = new TextField();
        tfOperatorId.setDisable(true);
        tfOperatorName = new TextField();

        btnCancel = new Button("Cancel");
        btnSave = new Button("Save");
        btnDelete = new Button("Delete");
    }

    private void layoutNodes() {
        hbButtons = new HBox(btnCancel, btnSave, btnDelete);

        this.getChildren().addAll(lblOperatorId, tfOperatorId, lblOperatorName, tfOperatorName, hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(hbButtons, 7);
        LayoutUtils.applyMarginsToChildren(this, 5);
        btnSave.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), null)));
        btnDelete.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), null)));
        btnSave.setTextFill(Color.WHITE);
        btnDelete.setTextFill(Color.WHITE);
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

    public TextField getTfOperatorName() {
        return tfOperatorName;
    }

    public TextField getTfOperatorId() {
        return tfOperatorId;
    }
}
