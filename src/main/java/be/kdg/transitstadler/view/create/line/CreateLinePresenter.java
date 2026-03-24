package be.kdg.transitstadler.view.create.line;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class CreateLinePresenter {
    private final TransitStadlerModel model;
    private final CreateLineView view;

    private boolean nameOk = false;

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
                checkDisableButton();
            }
        });

        view.getCbOperatorId().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkDisableButton();
            }
        });
    }

    private void updateView() {
        for (Operator currentOperator:model.getAllOperators()) {
            view.getCbOperatorId().getItems().add(currentOperator);
        }
        view.getBtnCreate().setDisable(true);
    }

    private void checkDisableButton() {
        if (view.getTxtLineName().getText().isEmpty() || view.getCbOperatorId().getSelectionModel().getSelectedItem() == null) {
            view.getBtnCreate().setDisable(true);
            return;
        }
        for (Line currentLine : model.getAllLinesByOperator(view.getCbOperatorId().getSelectionModel().getSelectedItem().operatorId())) {
            if (currentLine.lineName().equals(view.getTxtLineName().getText())) {
                view.getBtnCreate().setDisable(true);
                return;
            }
        }
        view.getBtnCreate().setDisable(false);
    }

}
