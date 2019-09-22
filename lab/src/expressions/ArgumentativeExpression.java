package expressions;

import expressions.util.Location;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ArgumentativeExpression<R extends Expression> extends AbstractExpression {
    private final List<R> arguments;

    public abstract String getName();
    public abstract boolean isConcrete();
    public abstract Location getLocation();

    @SafeVarargs
    protected ArgumentativeExpression(R... arguments) {
        this.arguments = Arrays.stream(arguments).collect(Collectors.toList());
    }

    public int getArity() {
        return arguments.size();
    }

    public R get(int index) {
        return arguments.get(index);
    }

    @SuppressWarnings("unused")
    public void change(int index, R expression) {
        arguments.set(index, expression);
    }
}
