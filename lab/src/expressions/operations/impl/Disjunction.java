package expressions.operations.impl;

import evaluators.Evaluators;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.util.Location;

import java.util.Map;

public class Disjunction extends Operation {

    public Disjunction(Logical first, Logical second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return Evaluators.disjunction.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.INFIX;
    }

    @Override
    public Disjunction clone() {
        return new Disjunction(get(0).clone(), get(1).clone());
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        Boolean[] argumentValues = {get(0).evaluate(values), get(1).evaluate(values)};
        return Evaluators.disjunction.evaluate(argumentValues);
    }
}
