package expressions.operations.impl;

import evaluators.LogicEvaluator;
import expressions.util.Location;
import expressions.Logical;
import expressions.operations.Operation;

import java.util.Map;

public final class Conjunction extends Operation {
    private static LogicEvaluator conjunction = new LogicEvaluator("&", args -> args[0] && args[1]);

    public Conjunction(Logical first, Logical second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return conjunction.getSymbol();
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
        return conjunction.evaluate(argumentValues);
    }
}
