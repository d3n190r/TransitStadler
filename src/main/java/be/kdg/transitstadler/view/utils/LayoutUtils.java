package be.kdg.transitstadler.view.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LayoutUtils {
    public static void applyMarginsToChildren(HBox container, double margin) {
        for (Node child : container.getChildren()) {
            HBox.setMargin(child, new javafx.geometry.Insets(margin));
        }
    }

    public static void applyMarginsToChildren(VBox container, double margin) {
        for (Node child : container.getChildren()) {
            VBox.setMargin(child, new javafx.geometry.Insets(margin));
        }
    }

    public static void applyMarginsToChildren(BorderPane container, double margin) {
        for (Node child : container.getChildren()) {
            BorderPane.setMargin(child, new Insets(margin));
        }
    }
}
