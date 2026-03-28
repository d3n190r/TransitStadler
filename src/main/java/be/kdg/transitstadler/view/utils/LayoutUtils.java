package be.kdg.transitstadler.view.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutUtils {
    public static void setupStage(Stage stage, Parent view, String title) {
        // TODO: move to presenter
        stage.setTitle(title);
        stage.setScene(new Scene(view));
        stage.setResizable(false);
        stage.sizeToScene();
    }

    /* applyMarginsToChildren gives all !ALREADY ADDED! children of the container the same margin.
     * Already made for the following containers:
     * - HBox
     * - VBox
     * - BorderPane
     */
    public static void applyMarginsToChildren(HBox hBox, double margin) {
        for (Node currentChild : hBox.getChildren()) {
            HBox.setMargin(currentChild, new javafx.geometry.Insets(margin));
        }
    }

    public static void applyMarginsToChildren(VBox vBox, double margin) {
        for (Node currentChild : vBox.getChildren()) {
            VBox.setMargin(currentChild, new javafx.geometry.Insets(margin));
        }
    }

    public static void applyMarginsToChildren(BorderPane borderPane, double margin) {
        for (Node currentChild : borderPane.getChildren()) {
            BorderPane.setMargin(currentChild, new Insets(margin));
        }
    }
}
