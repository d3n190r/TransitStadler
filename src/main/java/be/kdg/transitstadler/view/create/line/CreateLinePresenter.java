package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CreateLinePresenter {
    private final TransitStadlerModel model;
    private final CreateLineView view;

    public CreateLinePresenter(TransitStadlerModel model, CreateLineView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.addLine(view.getTxtLineName().getText(), view.getCbOperatorId().getSelectionModel().getSelectedItem().operatorId());
                view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {
        for (Operator currentOperator:model.getAllOperators()) {
            view.getCbOperatorId().getItems().add(currentOperator);
        }
    }

}
