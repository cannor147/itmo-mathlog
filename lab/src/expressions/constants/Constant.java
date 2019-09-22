package expressions.constants;

import expressions.AbstractExpression;
import expressions.Subjective;

import java.util.Map;

public class Constant extends AbstractExpression implements Subjective {
    private Object value;

    public Constant(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Object evaluate(Map<String, Object> values) {
        return value;
    }

    @Override
    public Constant clone() {
        return new Constant(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
