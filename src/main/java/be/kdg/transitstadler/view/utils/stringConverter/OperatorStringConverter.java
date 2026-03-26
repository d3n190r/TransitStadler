package be.kdg.transitstadler.view.utils.stringConverter;

import be.kdg.transitstadler.model.businessobject.Operator;
import javafx.util.StringConverter;

public class OperatorStringConverter extends StringConverter<Operator> {
    @Override
    public Operator fromString(String string) {return null;}

    @Override
    public String toString(Operator operator) {
        if (operator != null) {
            return operator.operatorName();
        }
        return null;
    }
}
