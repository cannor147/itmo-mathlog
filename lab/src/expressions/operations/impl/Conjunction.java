package expressions.operations.impl;

import evaluators.Evaluators;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.util.Location;

import java.util.Map;

public final class Conjunction extends Operation {
    public Conjunction(Logical first, Logical second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return Evaluators.conjunction.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.INFIX;
    }

    @Override
    public Conjunction clone() {
        return new Conjunction(get(0).clone(), get(1).clone());
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        Boolean[] argumentValues = {get(0).evaluate(values), get(1).evaluate(values)};
        return Evaluators.conjunction.evaluate(argumentValues);
    }
}
