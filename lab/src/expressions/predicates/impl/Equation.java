package expressions.predicates.impl;

import evaluators.Evaluators;
import expressions.util.Location;
import expressions.Subjective;
import expressions.predicates.Predicate;

import java.util.Map;

public class Equation extends Predicate {
    public Equation(Subjective first, Subjective second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return Evaluators.equality.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.INFIX;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Equation clone() {
        return new Equation(get(0).clone(), get(1).clone());
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        Object[] argumentValues = {get(0).evaluate(values), get(1).evaluate(values)};
        return Evaluators.equality.evaluate(argumentValues);
    }
}
