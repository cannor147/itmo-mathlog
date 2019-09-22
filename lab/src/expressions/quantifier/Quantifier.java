package expressions.quantifier;

import expressions.AbstractExpression;
import expressions.Logical;
import expressions.subject.SubjectVariable;

import java.util.Map;

public abstract class Quantifier extends AbstractExpression implements Logical {
    private final SubjectVariable variable;
    private final Logical expression;

    public Quantifier(SubjectVariable variable, Logical expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public abstract String getName();
    public abstract Quantifier clone();

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        throw new RuntimeException("Can't evaluate the quantifier.");
    }

    public SubjectVariable getVariable() {
        return variable;
    }

    public Logical getExpression() {
        return expression;
    }
}
