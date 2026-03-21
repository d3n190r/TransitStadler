package be.kdg.transitstadler.view.edit.operator;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public class EditOperatorPresenter {
    private final TransitStadlerModel model;
    private final EditOperatorView view;
    private final Operator operator;

    public EditOperatorPresenter(TransitStadlerModel model, EditOperatorView view, Operator operator) {
        this.model = model;
        this.view = view;
        this.operator = operator;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        this.view.getBtnSave().setOnAction(new  EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.updateOperator(operator.operatorId(), view.getTfOperatorName().getText());
                view.getScene().getWindow().hide();
            }
        });

        this.view.getBtnCancel().setOnAction(new  EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getScene().getWindow().hide();
            }
        });

        this.view.getBtnDelete().setOnAction(new  EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Operator");
                alert.setHeaderText("Do you really want to delete " + operator.operatorName() + "?");
                alert.showAndWait().ifPresent(new Consumer<>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                        if (buttonType == ButtonType.OK) {
                            model.deleteOperator(operator.operatorId());
                            view.getScene().getWindow().hide();
                        }
                    }
                });
            }
        });

        this.view.getTfOperatorName().setOnKeyTyped(new  EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String  text = view.getTfOperatorName().getText();
                if (text.isEmpty()) {
                    view.getBtnSave().setDisable(true);
                    return;
                }
                for (Operator currentOperator: model.getAllOperators()) {
                    if (currentOperator.operatorId() != operator.operatorId() && currentOperator.operatorName().equals(text)) {
                        view.getBtnSave().setDisable(true);
                        return;
                    }
                }
                view.getBtnSave().setDisable(false);
            }
        });
    }

    private void updateView() {
        this.view.getTfOperatorId().setText(String.valueOf(operator.operatorId()));
        this.view.getTfOperatorName().setText(operator.operatorName());
    }
}
