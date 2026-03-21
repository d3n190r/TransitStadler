package be.kdg.transitstadler.view.utils.cellFactory;

import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListViewOperatorCellFactory implements Callback<ListView<Operator>, ListCell<Operator>> {
    @Override
    public ListCell<Operator> call(ListView<Operator> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Operator operator, boolean empty) {
                super.updateItem(operator, empty);
                if (empty || operator == null) {
                    setText(null);
                } else {
                    setText(operator.operatorName());
                }
            }
        };
    }
}
