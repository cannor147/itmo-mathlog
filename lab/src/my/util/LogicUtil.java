package my.util;

import expressions.ArgumentativeExpression;
import expressions.Expression;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.predicates.Predicate;
import expressions.predicates.Variable;
import expressions.quantifier.Quantifier;
import expressions.subject.Function;
import expressions.subject.SubjectVariable;

import java.util.*;

import static my.theory.TheoryUtil.FIRST_GREEK_SYMBOLS;

public class LogicUtil {
    public static Set<Variable> collectVariables(Logical... expressions) {
        Set<Variable> result = new HashSet<>();
        for (Logical expression : expressions) {
            if (expression instanceof Variable) {
                result.add((Variable) expression.clone());
            } else if (expression instanceof Operation) {
                int n = ((Operation) expression).getArity();
                Logical[] arguments = new Logical[n];
                for (int i = 0; i < n; i++) {
                    arguments[i] = ((Operation) expression).get(i);
                }
                result.addAll(collectVariables(arguments));
            }
        }
        return result;
    }

    public static Set<SubjectVariable> collectFreeVariables(Expression... expressions) {
        Set<SubjectVariable> result = new HashSet<>();
        for (Expression expression : expressions) {
            collectFreeVariablesRecursively(expression, new HashSet<>(), result);
        }
        return result;
    }

    private static void collectFreeVariablesRecursively(Expression expression, Set<SubjectVariable> quantifierVariables, Set<SubjectVariable> freeVariables) {
        if (expression instanceof SubjectVariable && !quantifierVariables.contains(expression)) {
            freeVariables.add((SubjectVariable) expression);
        } else if (expression instanceof ArgumentativeExpression) {
            int n = ((ArgumentativeExpression) expression).getArity();
            for (int i = 0; i < n; i++) {
                collectFreeVariablesRecursively(((ArgumentativeExpression) expression).get(i), quantifierVariables, freeVariables);
            }
        } else if (expression instanceof Quantifier) {
            boolean was = quantifierVariables.contains(((Quantifier) expression).getVariable());
            quantifierVariables.add(((Quantifier) expression).getVariable());
            collectFreeVariablesRecursively(((Quantifier) expression).getExpression(), quantifierVariables, freeVariables);
            if (!was) {
                quantifierVariables.remove(((Quantifier) expression).getVariable());
            }
        }
    }

    public static Map<Character, String> createReplacing(Logical... expressions) {
        if (expressions.length > 3) {
            throw new RuntimeException("kek");
        }
        Map<Character, String> metaMapping = new HashMap<>();
        for (int i = 0; i < expressions.length; i++) {
            metaMapping.put(FIRST_GREEK_SYMBOLS[i], expressions[i].toString());
        }
        return metaMapping;
    }

    public static Map<Character, String> createReplacing(String... expr) {
        if (expr.length > 3) {
            throw new RuntimeException("kek");
        }
        Map<Character, String> metaMapping = new HashMap<>();
        for (int i = 0; i < expr.length; i++) {
            metaMapping.put(FIRST_GREEK_SYMBOLS[i], expr[i]);
        }
        return metaMapping;
    }

    public static Map<Character, Logical> createMapping(Logical... expressions) {
        if (expressions.length > 3) {
            throw new RuntimeException("kek");
        }
        Map<Character, Logical> metaMapping = new HashMap<>();
        for (int i = 0; i < expressions.length; i++) {
            metaMapping.put(FIRST_GREEK_SYMBOLS[i], expressions[i]);
        }
        return metaMapping;
    }
}
