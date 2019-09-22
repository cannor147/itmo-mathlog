package expressions.operations;

import expressions.ArgumentativeExpression;
import expressions.Logical;

public abstract class Operation extends ArgumentativeExpression<Logical> implements Logical {
    public Operation(Logical... arguments) {
        super(arguments);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }

    public abstract Operation clone();
}
