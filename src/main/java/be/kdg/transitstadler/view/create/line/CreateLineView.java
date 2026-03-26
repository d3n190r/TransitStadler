package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import be.kdg.transitstadler.view.utils.stringConverter.OperatorStringConverter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CreateLineView extends VBox {
    private Label lblLineName;
    private TextField txtLineName;
    private Label lblOperatorId;
    private ComboBox<Operator> cbOperatorId;
    private Button btnCancel;
    private Button btnCreate;

    HBox hbOperatorId;
    HBox hbLineName;
    HBox hbButtons;

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
        this.btnCancel = new Button("Cancel");
        this.btnCreate = new Button("Create");
    }

    private void layoutNodes() {
        this.hbLineName = new HBox(this.lblLineName, this.txtLineName);
        this.hbOperatorId = new HBox(this.lblOperatorId, this.cbOperatorId);
        this.hbButtons = new HBox(this.btnCancel, this.btnCreate);
        this.getChildren().addAll(this.hbLineName, this.hbOperatorId, this.hbButtons);
    }

    private void setNodeMarkup() {
        LayoutUtils.applyMarginsToChildren(this, 5);
        LayoutUtils.applyMarginsToChildren(this.hbLineName, 5);
        LayoutUtils.applyMarginsToChildren(this.hbOperatorId, 5);
        LayoutUtils.applyMarginsToChildren(this.hbButtons, 5);
        this.hbButtons.setAlignment(Pos.TOP_CENTER);
        this.btnCreate.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), null)));
    }

    public Button getBtnCancel() {return this.btnCancel;}

    public Button getBtnCreate() {return this.btnCreate;}

    public TextField getTxtLineName() {return this.txtLineName;}

    public ComboBox<Operator> getCbOperatorId() {return this.cbOperatorId;}
}
