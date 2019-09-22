package expressions.subject.impl;

import evaluators.ArithmeticEvaluator;
import expressions.util.Location;
import expressions.Subjective;
import expressions.subject.Function;

import java.util.Map;

public class Incrementation extends Function {
    private static ArithmeticEvaluator incrementation = new ArithmeticEvaluator("’", args -> args[0] + 1);

    public Incrementation(Subjective first) {
        super(first);
    }

    @Override
    public String getName() {
        return incrementation.getSymbol();
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
        return incrementation.evaluate(argumentValues);
    }
}
