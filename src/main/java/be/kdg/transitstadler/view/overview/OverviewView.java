package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class OverviewView extends BorderPane {
    protected Button btnSwitchOverview;

    protected Menu menuAdd;
    protected Menu menuEdit;

    protected MenuBar menuBar;

    protected MenuItem miAddLine;
    protected MenuItem miAddOperator;
    protected MenuItem miAddStation;
    protected MenuItem miEditLine;
    protected MenuItem miEditOperator;
    protected MenuItem miEditStation;

    protected HBox hbCenter;

    protected VBox vbBottom;
    protected VBox vbTop;

    protected OverviewView(String switchButtonName) {
        this.btnSwitchOverview = new Button(switchButtonName);
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.miAddLine = new MenuItem("Add Line");
        this.miAddOperator = new MenuItem("Add Operator");
        this.miAddStation = new MenuItem("Add Station");
        this.miEditLine = new MenuItem("Edit LIne");
        this.miEditOperator = new MenuItem("Edit Operator");
        this.miEditStation = new MenuItem("Edit Station");

        this.menuAdd = new Menu("Add");
        this.menuAdd.getItems().addAll(this.miAddLine, this.miAddOperator, this.miAddStation);
        this.menuEdit = new Menu("Edit");
        this.menuEdit.getItems().addAll(this.miEditLine, this.miEditOperator, this.miEditStation);

        this.menuBar = new MenuBar();
        this.menuBar.getMenus().addAll(this.menuEdit, this.menuAdd);
    }

    private void layoutNodes() {
        this.vbBottom = new VBox(this.btnSwitchOverview);
        this.hbCenter = new HBox();
        this.vbTop = new VBox(this.menuBar);

        this.setBottom(this.vbBottom);
        this.setCenter(this.hbCenter);
        this.setTop(this.vbTop);
    }

    private void setNodeMarkup() {
        this.menuBar.setMinWidth(600);

        this.btnSwitchOverview.setMinWidth(175);

        this.vbTop.setAlignment(Pos.TOP_CENTER);

        this.hbCenter.setAlignment(Pos.CENTER);

        this.vbBottom.setAlignment(Pos.TOP_CENTER);

        LayoutUtils.applyMarginsToChildren(this.vbBottom, 5);

        this.setPrefSize(600, 500);
    }

    public Button getBtnSwitchOverview() {return this.btnSwitchOverview;}

    public MenuItem getMiEditLine() {return this.miEditLine;}

    public MenuItem getMiEditOperator() {return this.miEditOperator;}

    public MenuItem getMiEditStation() {return this.miEditStation;}

    public MenuItem getMiAddLine() {return this.miAddLine;}

    public MenuItem getMiAddOperator() {return this.miAddOperator;}

    public MenuItem getMiAddStation() {return this.miAddStation;}

    public abstract Line getSelectedLine();

    public abstract Operator getSelectedOperator();

    public abstract Station getSelectedStation();
}
