package evaluators;

import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class Evaluator<T> {
    private final String symbol;
    private final Function<T[], T> evaluator;

    public Evaluator(String symbol, Function<T[], T> evaluator) {
        this.symbol = symbol;
        this.evaluator = evaluator;
    }

    public String getSymbol() {
        return symbol;
    }

    @SafeVarargs
    public final T evaluate(T... arguments) {
        return evaluator.apply(arguments);
    }
}

