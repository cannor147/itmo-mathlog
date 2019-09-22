package expressions;

import expressions.util.ExpressionUtil;

import java.util.Map;

public abstract class AbstractExpression implements Comparable<Expression>, Expression {
    private String cachedExpr;

    public AbstractExpression() {
        this.cachedExpr = null;
    }

    public abstract Object evaluate(Map<String, Object> values);
    public abstract Expression clone();

    @Override
    public int compareTo(Expression other) {
        return toString().compareTo(other.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Expression) {
            return toString().equals(obj.toString());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (cachedExpr == null) {
            cachedExpr = ExpressionUtil.transform(this);
        }
        return cachedExpr;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
