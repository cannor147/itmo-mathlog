package expressions.operations.impl;

import evaluators.LogicEvaluator;
import expressions.util.Location;
import expressions.Logical;
import expressions.operations.Operation;

import java.util.Map;

public class Implication extends Operation {
    private static LogicEvaluator implication = new LogicEvaluator("→", args -> !args[0] || args[1]);

    public Implication(Logical first, Logical second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return implication.getSymbol();
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
        return implication.evaluate(argumentValues);
    }
}
