package expressions.operations.impl;

import evaluators.Evaluators;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.util.Location;

import java.util.Map;

public class Negation extends Operation {

    public Negation(Logical first) {
        super(first);
    }

    @Override
    public String getName() {
        return Evaluators.negation.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.PREFIX_WITHOUT_COMMA;
    }

    @Override
    public Negation clone() {
        return new Negation(get(0).clone());
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        Boolean[] argumentValues = {get(0).evaluate(values)};
        return Evaluators.negation.evaluate(argumentValues);
    }
}
