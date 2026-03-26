package be.kdg.transitstadler.view.login;

import be.kdg.transitstadler.database.DatabaseConnector;
import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.view.overview.lineOverview.LineOverviewPresenter;
import be.kdg.transitstadler.view.overview.lineOverview.LineOverviewView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class LoginPresenter {
    private final TransitStadlerModel model;
    private final LoginView view;

    public LoginPresenter(TransitStadlerModel model, LoginView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnLogin().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnector.setServerLocation(view.getTfDatabase().getText());
                DatabaseConnector.setUsername(view.getTfUsername().getText());
                DatabaseConnector.setPassword(view.getTfPassword().getText());
                if (DatabaseConnector.getConnection() != null) {
                    LineOverviewView lineOverviewView = new LineOverviewView();
                    new LineOverviewPresenter(model, lineOverviewView);
                    view.getScene().setRoot(lineOverviewView);
                    lineOverviewView.getScene().getWindow().sizeToScene();
                }
            }
        });
    }

    private void updateView() {}
}
