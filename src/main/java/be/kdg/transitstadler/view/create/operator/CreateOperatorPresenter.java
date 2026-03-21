package be.kdg.transitstadler.view.create.operator;

import be.kdg.transitstadler.model.TransitStadlerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CreateOperatorPresenter {
    private final TransitStadlerModel model;
    private final CreateOperatorView view;

    public CreateOperatorPresenter(TransitStadlerModel model, CreateOperatorView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnCreateOperator().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.addOperator(view.getTxtOperatorName().getText());
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {

    }
}
