package expressions;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class LogicallyArgumentativeExpression extends ArgumentativeExpression<Logical> {
    protected LogicallyArgumentativeExpression(Logical... arguments) {
        super(Arrays.stream(arguments).collect(Collectors.toList()));
    }
}
