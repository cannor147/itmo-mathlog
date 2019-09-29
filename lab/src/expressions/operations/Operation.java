package expressions.operations;

import expressions.Logical;
import expressions.LogicallyArgumentativeExpression;

public abstract class Operation extends LogicallyArgumentativeExpression implements Logical {
    public Operation(Logical... arguments) {
        super(arguments);
    }

    @Override
    public boolean isConcrete() {
        return true;
    }

    public abstract Operation clone();
}
