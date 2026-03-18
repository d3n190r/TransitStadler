package be.kdg.transitstadler.view.edit;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class EditView extends HBox {
    private ListView<String> lvTest;

    private Label lblFirstName;
    private Label lblLastName;
    private Label lblClass;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfClass;

    private Button btnAdd;

    private VBox vbFields;

    private HBox hbAddBtn;

    private Region hSpacer;

    EditTypes editType;

    public EditView(EditTypes editType) {
        this.editType = editType;
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        lvTest = new ListView<>();

        lblFirstName = new Label(editType.name());
        lblLastName = new Label("Last Name");
        lblClass = new Label("Class");

        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfClass = new TextField();

        btnAdd = new Button("Add");

        hSpacer = new Region();
    }

    private void layoutNodes() {
        vbFields = new VBox();
        hbAddBtn = new HBox();

        hbAddBtn.getChildren().add(btnAdd);
        hbAddBtn.setAlignment(Pos.CENTER_RIGHT);

        vbFields.getChildren().addAll(lblFirstName, tfFirstName, lblLastName, tfLastName, lblClass, tfClass, hSpacer, hbAddBtn);
        this.getChildren().addAll(lvTest, vbFields);
        HBox.setHgrow(vbFields, Priority.SOMETIMES);
    }

    private void setNodeMarkup() {
        tfFirstName.setDisable(true);
        LayoutUtils.applyMarginsToChildren(vbFields, 3);
    }

    public List<String> getLvTest() {return lvTest.getItems();}
}
