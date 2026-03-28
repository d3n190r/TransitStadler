package be.kdg.transitstadler.presenter.create;

import be.kdg.transitstadler.model.TransitStadlerModel;
import be.kdg.transitstadler.model.businessobject.Line;
import be.kdg.transitstadler.model.businessobject.Operator;
import be.kdg.transitstadler.view.create.CreateLineView;
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
        this.view.getBtnCreate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.addLine(view.getTxtLineName().getText(), view.getCbOperatorId().getSelectionModel().getSelectedItem().operatorId());
                view.getScene().getWindow().hide();
            }
        });

        this.view.getTxtLineName().setOnKeyTyped(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {checkDisableButton();}
        });

        this.view.getCbOperatorId().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {checkDisableButton();}
        });

        this.view.getBtnCancel().setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {view.getScene().getWindow().hide();}
        });
    }

    private void updateView() {
        for (Operator currentOperator : this.model.getAllOperators()) {
            this.view.getCbOperatorId().getItems().add(currentOperator);
        }
        this.view.getBtnCreate().setDisable(true);
    }

    private void checkDisableButton() {
        if (this.view.getTxtLineName().getText().isEmpty() || this.view.getCbOperatorId().getSelectionModel().getSelectedItem() == null) {
            this.view.getBtnCreate().setDisable(true);
            return;
        }
        for (Line currentLine : this.model.getAllLinesByOperator(this.view.getCbOperatorId().getSelectionModel().getSelectedItem().operatorId())) {
            if (currentLine.lineName().equals(this.view.getTxtLineName().getText())) {
                this.view.getBtnCreate().setDisable(true);
                return;
            }
        }
        this.view.getBtnCreate().setDisable(false);
    }

}
