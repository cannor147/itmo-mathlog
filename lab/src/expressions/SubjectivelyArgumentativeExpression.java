package expressions;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class SubjectivelyArgumentativeExpression extends ArgumentativeExpression<Subjective> {
    protected SubjectivelyArgumentativeExpression(Subjective... arguments) {
        super(Arrays.stream(arguments).collect(Collectors.toList()));
    }
}
