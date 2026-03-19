package be.kdg.transitstadler.view;

import be.kdg.transitstadler.model.businessobject.Line;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class LineCellFactory implements Callback<ListView<Line>, ListCell<Line>> {
    @Override
    public ListCell<Line> call(ListView<Line> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Line line, boolean empty) {
                super.updateItem(line, empty);
                if (empty || line == null) {
                    setText(null);
                } else {
                    setText(line.lineName());
                }
            }
        };
    }
}

