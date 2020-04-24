package evaluators;

import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class Evaluator<T, R> {
    private final String symbol;
    private final Function<T[], R> evaluator;

    public Evaluator(String symbol, Function<T[], R> evaluator) {
        this.symbol = symbol;
        this.evaluator = evaluator;
    }

    public String getSymbol() {
        return symbol;
    }

    @SafeVarargs
    public final R evaluate(T... arguments) {
        return evaluator.apply(arguments);
    }
}

