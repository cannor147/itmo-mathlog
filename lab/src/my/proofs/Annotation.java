package my.proofs;

import expressions.Logical;
import my.Axiom;

import java.util.Arrays;

public class Annotation {
    private final Type type;
    private final Rule rule;
    private final Axiom axiom;
    @SuppressWarnings("unused") private final Logical hypothesis;
    private final int[] links;

    public Annotation(Axiom axiom) {
        this.type = Type.AXIOM;
        this.rule = null;
        this.axiom = axiom;
        this.hypothesis = null;
        this.links = new int[]{};
    }

    public Annotation(int index, Logical hypothesis) {
        this.type = Type.HYPOTHESIS;
        this.rule = null;
        this.axiom = null;
        this.hypothesis = hypothesis;
        this.links = new int[]{index};
    }

    public Annotation(Rule rule, int... links) {
        this.type = Type.RULE;
        this.rule = rule;
        this.axiom = null;
        this.hypothesis = null;
        this.links = links;
    }

    public Annotation() {
        this.type = Type.ERROR;
        this.rule = null;
        this.axiom = null;
        this.hypothesis = null;
        this.links = new int[]{};
    }

    public int[] findLineIndexes() {
        if (type == Type.RULE) {
            return links.clone();
        } else {
            return new int[0];
        }
    }

    public boolean hasError() {
        return type == Type.ERROR;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        if (type == Type.AXIOM) {
            return "Ax. sch. " + axiom.getName();
        } else if (type == Type.HYPOTHESIS) {
            return "Hypothesis " + (links[0] + 1);
        } else if (type == Type.RULE) {
            return rule.name + " " + transformToList(", ", Arrays.stream(links).mapToObj(x -> String.valueOf(x + 1)).toArray(String[]::new));
        } else {
            return "Unknown type";
        }
    }

    private static String transformToList(String spliterator, String... exprs) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (String expr : exprs) {
            if (!first) {
                stringBuilder.append(spliterator);
            }
            stringBuilder.append(expr);
            first = false;
        }
        return stringBuilder.toString();
    }

    public void changeIndexes(Integer[] indexMapping) {
        if (type == Type.RULE) {
            links[0] = indexMapping[links[0]];
            links[1] = indexMapping[links[1]];
        }
    }

    private enum Type {
        AXIOM,
        HYPOTHESIS,
        RULE,
        ERROR
    }

    public enum Rule {
        MODUS_PONENS("M.P."),
        PREDICATE_FIRST_RULE("P.R.1"),
        PREDICATE_SECOND_RULE("P.R.2");

        private final String name;

        Rule(String name) {
            this.name = name;
        }
    }
}
