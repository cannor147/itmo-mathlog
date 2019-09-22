package expressions.operations.impl;

import evaluators.LogicEvaluator;
import expressions.util.Location;
import expressions.Logical;
import expressions.operations.Operation;

import java.util.Map;

public class Negation extends Operation {
    private static LogicEvaluator negation = new LogicEvaluator("!", args -> !args[0]);

    public Negation(Logical first) {
        super(first);
    }

    @Override
    public String getName() {
        return negation.getSymbol();
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
        return negation.evaluate(argumentValues);
    }
}
