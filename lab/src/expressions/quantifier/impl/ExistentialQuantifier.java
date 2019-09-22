package expressions.quantifier.impl;

import expressions.Logical;
import expressions.quantifier.Quantifier;
import expressions.subject.SubjectVariable;

public class ExistentialQuantifier extends Quantifier {
    public ExistentialQuantifier(SubjectVariable variable, Logical expression) {
        super(variable, expression);
    }

    @Override
    public String getName() {
        return "?";
    }

    @Override
    public ExistentialQuantifier clone() {
        return new ExistentialQuantifier(getVariable().clone(), getExpression().clone());
    }
}
