package be.kdg.transitstadler.presenter;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PresenterUtils {
    public static void setupStage(Stage stage, Parent view, String title) {
        stage.setTitle(title);
        stage.setScene(new Scene(view));
        stage.setResizable(false);
        stage.sizeToScene();
    }
}
