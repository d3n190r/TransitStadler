package be.kdg.transitstadler.view.overview;

import be.kdg.transitstadler.view.utils.LayoutUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Igor Goossens (INF 101)
 */
public class OverviewView extends BorderPane {
    // private Node attributes (javafx.scene.control)
    private ListView<String> lvStationList;
    private ListView<String> lvLinesList;

    private Label lblLines;

    private Button btnEditStation;
    private Button btnEditLine;
    private Button btnEditBoth;

    // private Node attributes (javafx.scene.layout)
    private HBox hbMain;
    private HBox hbEditButtons;

    private VBox vbLinesAndButtons;

    public OverviewView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    /**
     * Creates all the required nodes for the view (this doesn't include the panes).
     */
    private void initialiseNodes() {
        // ListView //
        lvStationList = new ListView<>();

        lvLinesList = new ListView<>();

        // Label //
        lblLines = new Label("Lines:");

        // Button //
        btnEditStation = new Button("Edit Station");
        btnEditLine = new Button("Edit Line");
        btnEditBoth = new Button("Edit Both");
    }

    /**
     * Creates the panes and adds the nodes from this.initialiseNodes() to them.
     */
    private void layoutNodes() {
        // Panes //
        hbMain = new HBox();

        vbLinesAndButtons = new VBox();
        hbEditButtons = new HBox();

        // Add to Panes //
        hbEditButtons.getChildren().addAll(btnEditStation, btnEditLine, btnEditBoth);

        vbLinesAndButtons.getChildren().addAll(lblLines, lvLinesList, hbEditButtons);

        hbMain.getChildren().add(lvStationList);
        hbMain.getChildren().add(vbLinesAndButtons);

        this.setCenter(hbMain);
    }

    /**
     * Changes the appearance of the nodes created in this.initialiseNodes() & this.layoutNodes().
     */
    private void setNodeMarkup() {
        // Set visual space between Nodes //
        LayoutUtils.applyMarginsToChildren(this, 10);
        LayoutUtils.applyMarginsToChildren(hbMain, 10);
        LayoutUtils.applyMarginsToChildren(hbEditButtons, 5);

        // Some improvements to the Lines (right) part of the screen //
        vbLinesAndButtons.setAlignment(Pos.TOP_CENTER);
        lblLines.setStyle("-fx-font-weight: bold");
        lblLines.setPadding(new Insets(0, 0, 5, 0));
    }

    // TODO: Documentation
    public ListView<String> getLvLinesList() {
        return lvLinesList;
    }

    // TODO: Documentation
    public ListView<String> getLvStationList() {
        return lvStationList;
    }
}
