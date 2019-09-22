package expressions.quantifier.impl;

import expressions.Logical;
import expressions.quantifier.Quantifier;
import expressions.subject.SubjectVariable;

public class UniversalQuantifier extends Quantifier {
    public UniversalQuantifier(SubjectVariable variable, Logical expression) {
        super(variable, expression);
    }

    @Override
    public String getName() {
        return "@";
    }

    @Override
    public UniversalQuantifier clone() {
        return new UniversalQuantifier(getVariable().clone(), getExpression().clone());
    }
}
