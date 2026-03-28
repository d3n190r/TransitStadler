package be.kdg.transitstadler.view.edit;

import be.kdg.transitstadler.model.businessobject.Station;
import be.kdg.transitstadler.view.utils.stringConverter.StationStringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

public class AddStopView extends FlowPane {
    private ComboBox<Station> cbStations;

    public AddStopView() {
        this.initialiseNodes();
        this.layoutNodes();
        this.setNodeMarkup();
    }

    private void initialiseNodes() {
        this.cbStations = new ComboBox<>();
        this.cbStations.setConverter(new StationStringConverter());
    }

    private void layoutNodes() {this.getChildren().add(this.cbStations);}

    private void setNodeMarkup() {}

    public ComboBox<Station> getCbStations() {return this.cbStations;}
}
