package expressions.subject.impl;

import evaluators.ArithmeticEvaluator;
import evaluators.Evaluators;
import expressions.util.Location;
import expressions.Subjective;
import expressions.subject.Function;

import java.util.Map;

public class Addition extends Function {

    public Addition(Subjective first, Subjective second) {
        super(first, second);
    }

    @Override
    public String getName() {
        return Evaluators.addition.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.INFIX;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Addition clone() {
        return new Addition(get(0).clone(), get(1).clone());
    }

    @Override
    public Integer evaluate(Map<String, Object> values) {
        Integer[] argumentValues = {(Integer) get(0).evaluate(values), (Integer) get(1).evaluate(values)};
        return Evaluators.addition.evaluate(argumentValues);
    }
}
