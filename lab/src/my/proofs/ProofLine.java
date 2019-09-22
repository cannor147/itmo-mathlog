package my.proofs;

import expressions.Logical;

import static my.theory.TheoryUtil.formalArithmetic;

public class ProofLine {
    private String expr;
    private Logical expression;

    public ProofLine(final String expr) {
        this.expr = expr;
        this.expression = null;
    }

    public ProofLine(final Logical expression) {
        this.expr = null;
        this.expression = expression;
    }

    public Logical getExpression() {
        if (expression == null) {
            // todo: replace with something more general
            expression = formalArithmetic.parse(expr);
        }
        return expression;
    }

    @Override
    public String toString() {
//        if (expr == null) {
//            return getExpression().toString();
//        } else {
//            return expr;
//        }
        if (expression == null) {
            return expr;
        } else {
            return getExpression().toString();
        }
    }
}
