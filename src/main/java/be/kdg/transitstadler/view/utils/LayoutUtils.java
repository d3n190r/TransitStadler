package be.kdg.transitstadler.view.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Igor Goossens (INF 101)
 */
public class LayoutUtils {
    /* applyMarginsToChildren gives all !ALREADY ADDED! children of the container the same margin.
     * Already made for the following containers:
     * - HBox
     * - VBox
     * - BorderPane
     */

    /**
     * Sets a given margin to all the elements currently in the container.
     * @param hBox The hbox whose elements should have the margin.
     * @param margin The amount of margin to set.
     */
    public static void applyMarginsToChildren(HBox hBox, double margin) {
        for (Node currentChild : hBox.getChildren()) {
            HBox.setMargin(hBox, new javafx.geometry.Insets(margin));
        }
    }

    /**
     * Sets a given margin to all the elements currently in the container.
     * @param vBox The vbox whose elements should have the margin.
     * @param margin The amount of margin to set.
     */
    public static void applyMarginsToChildren(VBox vBox, double margin) {
        for (Node currentChild : vBox.getChildren()) {
            VBox.setMargin(currentChild, new javafx.geometry.Insets(margin));
        }
    }

    /**
     * Sets a given margin to all the elements currently in the container.
     * @param borderPane The borderpane whose elements should have the margin.
     * @param margin The amount of margin to set.
     */
    public static void applyMarginsToChildren(BorderPane borderPane, double margin) {
        for (Node currentChild : borderPane.getChildren()) {
            BorderPane.setMargin(currentChild, new Insets(margin));
        }
    }
}
