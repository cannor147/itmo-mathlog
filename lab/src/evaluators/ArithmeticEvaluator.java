package evaluators;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class ArithmeticEvaluator extends Evaluator<Integer> {
    public ArithmeticEvaluator(String symbol, Function<Integer[], Integer> evaluator) {
        super(symbol, evaluator);
    }
}
