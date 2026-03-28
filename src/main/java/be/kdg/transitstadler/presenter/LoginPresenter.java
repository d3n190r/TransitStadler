package be.kdg.transitstadler.presenter;

import be.kdg.transitstadler.database.DatabaseConnector;
import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.presenter.overview.LineOverviewPresenter;
import be.kdg.transitstadler.view.LoginView;
import be.kdg.transitstadler.view.overview.LineOverviewView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
                DatabaseConnector.setServerLocation(view.getCbDatabase().getSelectionModel().getSelectedItem());
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
