package expressions.predicates.impl;

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
        return "=";
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
        return get(0).evaluate(values).equals(get(1).evaluate(values));
    }
}
