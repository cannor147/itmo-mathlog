package expressions.subject;

import expressions.AbstractExpression;
import expressions.Subjective;

import java.util.Map;

public class SubjectVariable extends AbstractExpression implements Subjective {
    private final String name;

    public SubjectVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(Map<String, Object> values) {
        return values.get(name);
    }

    @Override
    public SubjectVariable clone() {
        return new SubjectVariable(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
