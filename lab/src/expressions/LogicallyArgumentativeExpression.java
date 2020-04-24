package expressions;

import expressions.util.Location;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class LogicallyArgumentativeExpression extends ArgumentativeExpression<Logical> {
    private final Logical[] arguments;

    public abstract String getName();
    public abstract boolean isConcrete();
    public abstract Location getLocation();

    public LogicallyArgumentativeExpression(Logical... arguments) {
        this.arguments = arguments;
    }

    public int getArity() {
        return arguments.length;
    }

    public Logical get(int index) {
        return arguments[index];
    }

    @SuppressWarnings("unused")
    public void change(int index, Logical expression) {
        arguments[index] = expression;
    }
}
