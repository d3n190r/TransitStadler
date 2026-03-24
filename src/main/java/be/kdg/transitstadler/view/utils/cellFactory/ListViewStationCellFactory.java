package be.kdg.transitstadler.view.utils.cellFactory;

import be.kdg.transitstadler.model.businessobject.Station;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ListViewStationCellFactory implements Callback<ListView<Station>, ListCell<Station>> {
    @Override
    public ListCell<Station> call(ListView<Station> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Station line, boolean empty) {
                super.updateItem(line, empty);
                if (empty || line == null) {
                    setText(null);
                } else {
                    setText(line.stationName());
                }
            }
        };
    }
}

