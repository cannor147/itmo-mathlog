package exceptions;

import expressions.Expression;

public class UnprovenExpressionException extends Exception {

    public UnprovenExpressionException(Expression expression) {
        super("Cannot prove " + expression.toString());
    }

    public UnprovenExpressionException(Expression expression, Throwable throwable) {
        super("Cannot prove " + expression.toString(), throwable);
    }
}
