package expressions.predicates;

import expressions.AbstractExpression;
import expressions.Logical;

import java.util.Map;

public class Variable extends AbstractExpression implements Logical {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        return (Boolean) values.get(name);
    }

    @Override
    public Variable clone() {
        return new Variable(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
