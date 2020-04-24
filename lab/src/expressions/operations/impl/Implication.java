package expressions.operations.impl;

import evaluators.Evaluators;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.util.Location;

import java.util.Map;

public class Implication extends Operation {

    public Implication(Logical first, Logical second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return Evaluators.implication.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.INFIX;
    }

    @Override
    public Implication clone() {
        return new Implication(get(0).clone(), get(1).clone());
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        Boolean[] argumentValues = {get(0).evaluate(values), get(1).evaluate(values)};
        return Evaluators.implication.evaluate(argumentValues);
    }
}
