package expressions;

import expressions.util.Location;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ArgumentativeExpression<R extends Expression> extends AbstractExpression {
    public abstract String getName();
    public abstract boolean isConcrete();
    public abstract Location getLocation();

    public abstract int getArity();

    public abstract R get(int index);

    @SuppressWarnings("unused")
    public abstract void change(int index, R expression);
}
