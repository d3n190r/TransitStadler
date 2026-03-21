package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

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

        view.getTxtLineName().setOnKeyTyped(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String text = view.getTxtLineName().getText();
                if (text.isEmpty()) {
                    view.getBtnCreate().setDisable(true);
                    return;
                }
                for (Line currentLine : model.getAllLines()) {
                    if (currentLine.lineName().equals(text)) {
                        view.getBtnCreate().setDisable(true);
                        return;
                    }
                }
                view.getBtnCreate().setDisable(false);
            }
        });
    }

    private void updateView() {
        for (Operator currentOperator:model.getAllOperators()) {
            view.getCbOperatorId().getItems().add(currentOperator);
        }
        view.getBtnCreate().setDisable(true);
    }

}
