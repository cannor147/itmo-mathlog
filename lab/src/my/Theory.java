package my;

import expressions.Logical;
import my.theory.PreAxiom;
import parser.LogicalParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class Theory {
    private final LogicalParser parser;
    private final Axiom[] axioms;

    protected Theory(LogicalParser parser, PreAxiom... axioms) {
        this.parser = parser;
        this.axioms = Arrays.stream(axioms).map(preAxiom -> new Axiom(preAxiom.getName(), parser.parse(preAxiom.getPattern()),
                preAxiom.getMetaVariables(), preAxiom.getSubstitutions())).toArray(Axiom[]::new);
    }

    public Axiom[] getAxioms() {
        return axioms;
    }

    public boolean isAxiom(Logical expression) {
        return checkForAxiom(expression) != null;
    }

    public Axiom checkForAxiom(Logical expression) {
        for (Axiom axiom : getAxioms()) {
            if (axiom.checkForImplementation(expression)) {
                return axiom;
            }
        }
        return null;
    }

    public Theorem createTheorem(Logical expression, List<Logical> hypotheses) {
        return new Theorem(this, expression, hypotheses);
    }

    public Theorem createTheorem(Logical expression) {
        return createTheorem(expression, new ArrayList<>());
    }

    public Logical parse(String expr) {
        return parser.parse(expr);
    }

    public abstract void generateProof(Theorem theorem) throws Exception;
}
