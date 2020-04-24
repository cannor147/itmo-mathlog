package evaluators;

import java.util.function.Function;

public class PredicateEvaluator extends Evaluator<Object, Boolean> {
    public PredicateEvaluator(String symbol, Function<Object[], Boolean> evaluator) {
        super(symbol, evaluator);
    }
}
