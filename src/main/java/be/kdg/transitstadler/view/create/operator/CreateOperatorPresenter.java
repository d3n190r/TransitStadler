package be.kdg.transitstadler.view.create.operator;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

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

        view.getTxtOperatorName().setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String text = view.getTxtOperatorName().getText();
                if (text.isEmpty()) {
                    view.getBtnCreateOperator().setDisable(true);
                    return;
                }
                for (Operator currentOperator : model.getAllOperators()) {
                    if (currentOperator.operatorName().equals(text)) {
                        view.getBtnCreateOperator().setDisable(true);
                        return;
                    }
                }
                view.getBtnCreateOperator().setDisable(false);
            }
        });
    }

    private void updateView() {
        view.getBtnCreateOperator().setDisable(true);
    }
}
