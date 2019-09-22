package my;

import expressions.ArgumentativeExpression;
import expressions.Expression;
import expressions.Logical;
import expressions.Subjective;
import expressions.constants.Constant;
import expressions.operations.Operation;
import expressions.predicates.Predicate;
import expressions.predicates.Variable;
import expressions.quantifier.Quantifier;
import expressions.subject.Function;
import expressions.subject.SubjectVariable;
import expressions.util.ExpressionUtil;
import my.util.LogicUtil;
import parser.impl.ArithmeticalParser;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class Axiom {
    private static final ArithmeticalParser arithmeticParser = new ArithmeticalParser();

    private final String name;
    private final Logical pattern;
    private final Map<Character, Class> metaVariables;
    private final Map<Character, Map<Character, Map<Integer, String>>> substitutions;

    public Axiom(String name, Logical pattern, Map<Character, Class> metaVariables, Map<Character, Map<Character, Map<Integer, String>>> substitutions) {
        this.name = name;
        this.pattern = pattern;
        this.metaVariables = metaVariables;
        this.substitutions = substitutions;
    }

    public String getName() {
        return name;
    }

    public Logical implement(Map<Character, Logical> metaMapping) {
        Logical expression = pattern.clone();
        metaMapping.replaceAll((metaVariable, e) -> e.clone());
        if (expression instanceof Variable) {
            return metaMapping.get(((Variable) expression).getName().charAt(0));
        }

        Queue<Logical> queue = new ArrayDeque<>();
        queue.add(expression);
        while (!queue.isEmpty()) {
            Logical current = queue.poll();
            if (current instanceof Operation) {
                int n = ((Operation) current).getArity();
                for (int i = 0; i < n; i++) {
                    Logical argument = ((Operation) current).get(i);
                    if (argument instanceof Variable) {
                        ((Operation) current).change(i, metaMapping.get(((Variable) argument).getName().charAt(0)));
                    } else {
                        queue.add(argument);
                    }
                }
            } else {
                throw new RuntimeException("kek");
            }
        }
        return expression;
    }

    public boolean checkForImplementation(Logical expression) {
        Map<Character, List<Expression>> metaMapping = new HashMap<>();
        try {
            lol(pattern, expression, this.metaVariables, metaMapping);
            for (Map.Entry<Character, List<Expression>> entry : metaMapping.entrySet()) {
                Character onlyOne = null;
                Expression main = null;
                Map<Integer, String> uuu = null;
                boolean pep = substitutions.containsKey(entry.getKey());
                Set<SubjectVariable> forbiddenVariables = null;
                if (pep) {
                    if (substitutions.get(entry.getKey()).size() > 1) {
                        throw new RuntimeException("kek");
                    }
                    for (Map.Entry<Character, Map<Integer, String>> kek : substitutions.get(entry.getKey()).entrySet()) {
                        onlyOne = kek.getKey();
                        uuu = kek.getValue();
                        for (Map.Entry<Integer, String> azaza : kek.getValue().entrySet()) {
                            if (azaza.getValue().length() == 1 && azaza.getValue().charAt(0) == onlyOne) {
                                main = entry.getValue().get(azaza.getKey() - 1);
                                break;
                            }
                        }
                    }
                    assert uuu != null;
                    forbiddenVariables = findBusyVariablesForVariable(main, (SubjectVariable) metaMapping.get(onlyOne).get(0), new HashSet<>());
                } else {
                    main = entry.getValue().get(0);
                }

                for (int i = 0; i < entry.getValue().size(); i++) {
                    Expression current = entry.getValue().get(i);
                    assert main != null;
                    Expression modifiedMain = main.clone();
                    if (pep) {
                        SubjectVariable subjectVariable = new SubjectVariable("" + onlyOne);
                        exchange(modifiedMain, (SubjectVariable) metaMapping.get(onlyOne).get(0), subjectVariable);
                        if (uuu.get(i + 1).equals("*")) {
                            Map<Character, Class> map = new HashMap<>();
                            map.put(subjectVariable.getName().charAt(0), Subjective.class);
                            Map<Character, List<Expression>> localMetaMapping = new HashMap<>();
                            lol(modifiedMain, current, map, localMetaMapping);
                            for (List<Expression> localExpressions : localMetaMapping.values()) {
                                for (Expression localExpression : localExpressions) {
                                    Set<SubjectVariable> freeLocalVariables = LogicUtil.collectFreeVariables(localExpression);
                                    for (SubjectVariable freeLocalVariable : freeLocalVariables) {
                                        if (forbiddenVariables.contains(freeLocalVariable)) {
                                            return false;
                                        }
                                    }
                                    if (!localExpression.equals(localExpressions.get(0))) {
                                        return false;
                                    }
                                }
                            }
                            continue;
                        }
                        exchange(modifiedMain, subjectVariable, arithmeticParser.parse(uuu.get(i + 1)));
                        exchange(modifiedMain, subjectVariable, (SubjectVariable) metaMapping.get(onlyOne).get(0));
                    } else {
                        if (i == 0) {
                            continue;
                        }
                    }
                    if (!modifiedMain.equals(current)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Set<SubjectVariable> findBusyVariablesForVariable(Expression expression, SubjectVariable variable, Set<SubjectVariable> busyVariables) {
        if (expression instanceof SubjectVariable) {
            if (((SubjectVariable) expression).getName().equals(variable.getName())) {
                return new HashSet<>(busyVariables);
            }
        } else if (expression instanceof ArgumentativeExpression) {
            int n = ((ArgumentativeExpression) expression).getArity();
            Set<SubjectVariable> result = new HashSet<>();
            for (int i = 0; i < n; i++) {
                Expression argument = ((ArgumentativeExpression) expression).get(i);
                result.addAll(findBusyVariablesForVariable(argument, variable, busyVariables));
            }
            return result;
        } else if (expression instanceof Quantifier) {
            if (!((Quantifier) expression).getVariable().getName().equals(variable.getName())) {
                busyVariables.add(((Quantifier) expression).getVariable());
                return findBusyVariablesForVariable(((Quantifier) expression).getExpression(), variable, busyVariables);
            }
        }
        return new HashSet<>();
    }

    private void exchange(Expression current, SubjectVariable target, Subjective replacement) {
        if (current instanceof Quantifier) {
            if (!((Quantifier) current).getName().equals(target.getName())) {
                exchange(((Quantifier) current).getExpression(), target, replacement);
            }
        } else if (current instanceof ArgumentativeExpression) {
            int n = ((ArgumentativeExpression) current).getArity();
            for (int i = 0; i < n; i++) {
                Expression argument = ((ArgumentativeExpression) current).get(i);
                if (argument instanceof SubjectVariable && ((SubjectVariable) argument).getName().equals(target.getName())) {
                    if (current instanceof Predicate) {
                        ((Predicate) current).change(i, replacement);
                    } else if (current instanceof Function) {
                        ((Function) current).change(i, replacement);
                    }
                } else {
                    exchange(((ArgumentativeExpression) current).get(i), target, replacement);
                }
            }
        }
    }

    private void lol(Expression axiomPart, Expression expressionPart, Map<Character, Class> metaVariables, Map<Character, List<Expression>> metaMapping) throws Exception {
        if (axiomPart instanceof Variable) {
            String variableName = ((Variable) axiomPart).getName();
            if ((variableName.length() > 1 || !metaVariables.containsKey(variableName.charAt(0)))) {
                if (expressionPart instanceof Variable && ((Variable) expressionPart).getName().equals(variableName)) {
                    return;
                } else {
                    throw new Exception("kek");
                }
            }
            if (!ExpressionUtil.isInstanceOf(expressionPart, metaVariables.get(variableName.charAt(0)))) {
                throw new Exception("kek");
            }
            metaMapping.computeIfAbsent(variableName.charAt(0), k -> new ArrayList<>());
            metaMapping.get(variableName.charAt(0)).add(expressionPart);
        } else if (axiomPart instanceof SubjectVariable) {
            String variableName = ((SubjectVariable) axiomPart).getName();
            if ((variableName.length() > 1 || !metaVariables.containsKey(variableName.charAt(0)))) {
                if (expressionPart instanceof SubjectVariable && ((SubjectVariable) expressionPart).getName().equals(variableName)) {
                    return;
                } else {
                    throw new Exception("kek");
                }
            }
            if (!ExpressionUtil.isInstanceOf(expressionPart, metaVariables.get(variableName.charAt(0)))) {
                throw new Exception("kek");
            }
            metaMapping.computeIfAbsent(variableName.charAt(0), k -> new ArrayList<>());
            metaMapping.get(variableName.charAt(0)).add(expressionPart);
        } else if (axiomPart instanceof Constant) {
            if (!ExpressionUtil.isInstanceOf(expressionPart, axiomPart.getClass())) {
                throw new Exception("kek");
            }
        } else if (axiomPart instanceof Quantifier) {
            if (!ExpressionUtil.isInstanceOf(expressionPart, axiomPart.getClass())) {
                throw new Exception("kek");
            }
            lol(((Quantifier) axiomPart).getVariable(), ((Quantifier) expressionPart).getVariable(), metaVariables, metaMapping);
            lol(((Quantifier) axiomPart).getExpression(), ((Quantifier) expressionPart).getExpression(), metaVariables, metaMapping);
        } else if (axiomPart instanceof ArgumentativeExpression) {
            if (!ExpressionUtil.isInstanceOf(expressionPart, axiomPart.getClass())) {
                throw new Exception("kek");
            }
            int n = ((ArgumentativeExpression) expressionPart).getArity();
            for (int i = 0; i < n; i++) {
                lol(((ArgumentativeExpression) axiomPart).get(i), ((ArgumentativeExpression) expressionPart).get(i), metaVariables, metaMapping);
            }
        }
    }
}
