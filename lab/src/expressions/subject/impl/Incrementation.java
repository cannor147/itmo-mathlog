package expressions.subject.impl;

import evaluators.ArithmeticEvaluator;
import evaluators.Evaluators;
import expressions.util.Location;
import expressions.Subjective;
import expressions.subject.Function;

import java.util.Map;

public class Incrementation extends Function {

    public Incrementation(Subjective first) {
        super(first);
    }

    @Override
    public String getName() {
        return Evaluators.incrementation.getSymbol();
    }

    @Override
    public Location getLocation() {
        return Location.POSTFIX_WITHOUT_COMMA;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Incrementation clone() {
        return new Incrementation(get(0).clone());
    }

    @Override
    public Integer evaluate(Map<String, Object> values) {
        Integer[] argumentValues = {(Integer) get(0).evaluate(values)};
        return Evaluators.incrementation.evaluate(argumentValues);
    }
}
