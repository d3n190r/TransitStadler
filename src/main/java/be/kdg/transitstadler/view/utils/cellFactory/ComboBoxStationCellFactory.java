package be.kdg.transitstadler.view.utils.cellFactory;

import be.kdg.transitstadler.model.businessobject.Station;
import javafx.util.StringConverter;

public class ComboBoxStationCellFactory extends StringConverter<Station> {
    @Override
    public Station fromString(String string) {
        return null;
    }

    @Override
    public String toString(Station operator) {
        if (operator != null) {
            return operator.stationName();
        }
        return null;
    }
}
