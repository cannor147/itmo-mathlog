package evaluators;

import java.util.function.Function;

public class LogicEvaluator extends Evaluator<Boolean, Boolean> {
    public LogicEvaluator(String symbol, Function<Boolean[], Boolean> evaluator) {
        super(symbol, evaluator);
    }
}
