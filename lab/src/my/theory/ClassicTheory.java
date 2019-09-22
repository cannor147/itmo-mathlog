package my.theory;

import exceptions.UnprovenExpressionException;
import expressions.Logical;
import expressions.operations.Operation;
import expressions.operations.impl.Conjunction;
import expressions.operations.impl.Disjunction;
import expressions.operations.impl.Implication;
import expressions.operations.impl.Negation;
import expressions.predicates.Variable;
import my.Theorem;
import my.Theory;
import my.proofs.Proof;
import my.proofs.ProofLine;
import my.util.LemmaUtil;
import my.util.LogicUtil;
import parser.LogicalParser;

import javax.naming.SizeLimitExceededException;
import java.util.*;
import java.util.stream.Collectors;

import static my.util.LemmaUtil.*;
import static my.util.LogicUtil.createMapping;
import static my.util.LogicUtil.createReplacing;

public class ClassicTheory extends Theory {
    ClassicTheory(LogicalParser parser, TheoryUtil.PreAxiom... axioms) {
        super(parser, axioms);
    }

    @Override
    public void generateProof(Theorem theorem) throws Exception {
        if (theorem.getHypotheses().size() == 0) {
            theorem.setProof(generateProofWithoutHypotheses(theorem));
        } else {
            for (Logical hypothesis : theorem.getHypotheses()) {
                if (!((hypothesis instanceof Negation && ((Negation) hypothesis).get(0) instanceof Variable) || hypothesis instanceof Variable)) {
                    throw new Exception("kek");
                }
            }
            theorem.setProof(generateProofWithSimpleHypotheses(theorem));
        }
    }

    public Theorem theoremGlivenko(Theorem intuitionisticTheorem) throws Exception {
        if (intuitionisticTheorem.getProof() == null) {
            throw new Exception("kek");
        }

        Theorem theorem = createTheorem(new Negation(new Negation(intuitionisticTheorem.getExpression())), intuitionisticTheorem.getHypotheses());
        List<ProofLine> lines = new ArrayList<>();

        List<ProofLine> intuitionisticLines = intuitionisticTheorem.getProof().getLines();
        for (int i = 0; i < intuitionisticLines.size(); i++) {
            ProofLine line = intuitionisticLines.get(i);
            boolean isTenthAxiom = getAxioms()[9].checkForImplementation(line.getExpression());
            if (theorem.isHypothesis(line.getExpression()) || (isAxiom(line.getExpression()) && !isTenthAxiom)) {
                String alpha = line.getExpression().toString();
                lines.addAll(Arrays.stream(notNotALemma.replace(LogicUtil.createReplacing(alpha))).map(ProofLine::new).collect(Collectors.toList()));
            } else if (isTenthAxiom) {
                String alpha = ((Implication) line.getExpression()).get(1).toString();
                lines.addAll(Arrays.stream(notNotTenthAxiomLemma.replace(LogicUtil.createReplacing(alpha))).map(ProofLine::new).collect(Collectors.toList()));
            } else if (intuitionisticTheorem.isModusPonens(i)) {
                Implication impl = (Implication) intuitionisticTheorem.findModusPonens(i).getExpression();
                lines.addAll(Arrays.stream(notNotModusPonensLemma.replace(createReplacing(impl.get(0), impl.get(1)))).map(ProofLine::new).collect(Collectors.toList()));
            }
        }
        theorem.setProof(new Proof(lines));
        return theorem;
    }

    public Theorem findMinimumHypotheses(Logical expression) throws UnprovenExpressionException, SizeLimitExceededException {
        List<Variable> variables = new ArrayList<>(LogicUtil.collectVariables(expression));
        if (variables.size() > 3) {
            throw new SizeLimitExceededException("Cannot create a proof of expression with more than three variables");
        }
        List<Negation> negations = new ArrayList<>();
        for (Variable variable : variables) {
            negations.add(new Negation(variable));
        }

        while (variables.size() < 3) {
            variables.add(new Variable("`" + variables.size()));
        }
        boolean checkTrue = true, checkFalse = false;
        boolean[][][] values = new boolean[2][2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put(variables.get(0).getName(), i % 2 == 1);
                    map.put(variables.get(1).getName(), j % 2 == 1);
                    map.put(variables.get(2).getName(), k % 2 == 1);

                    values[i][j][k] = expression.evaluate(map);
                    checkTrue &= values[i][j][k];
                    checkFalse |= values[i][j][k];
                }
            }
        }

        List<Logical> hypotheses;
        if (checkTrue) {
            hypotheses = Collections.emptyList();
        } else if (values[1][0][0] && values[1][0][1] && values[1][1][0] && values[1][1][1]) {
            hypotheses = Collections.singletonList(variables.get(0));
        } else if (values[0][1][0] && values[0][1][1] && values[1][1][0] && values[1][1][1]) {
            hypotheses = Collections.singletonList(variables.get(1));
        } else if (values[0][0][1] && values[0][1][1] && values[1][0][1] && values[1][1][1]) {
            hypotheses = Collections.singletonList(variables.get(2));
        } else if (values[1][1][0] & values[1][1][1]) {
            hypotheses = Arrays.asList(variables.get(0), variables.get(1));
        } else if (values[1][0][1] & values[1][1][1]) {
            hypotheses = Arrays.asList(variables.get(0), variables.get(2));
        } else if (values[0][1][1] & values[1][1][1]) {
            hypotheses = Arrays.asList(variables.get(1), variables.get(2));
        } else if (values[1][1][1]) {
            hypotheses = new ArrayList<>(variables);
        } else {
            expression = new Negation(expression);
            if (!checkFalse) {
                hypotheses = Collections.emptyList();
            } else if (!values[0][0][0] && !values[0][0][1] && !values[0][1][0] && !values[0][1][1]) {
                hypotheses = Collections.singletonList(negations.get(0));
            } else if (!values[0][0][0] && !values[0][0][1] && !values[1][0][0] && !values[1][0][1]) {
                hypotheses = Collections.singletonList(negations.get(1));
            } else if (!values[0][0][0] && !values[0][1][0] && !values[1][0][0] && !values[1][1][0]) {
                hypotheses = Collections.singletonList(negations.get(2));
            } else if (!values[0][0][0] & !values[0][0][1]) {
                hypotheses = Arrays.asList(negations.get(0), negations.get(1));
            } else if (!values[0][0][0] & !values[0][1][0]) {
                hypotheses = Arrays.asList(negations.get(0), negations.get(2));
            } else if (!values[0][0][0] & !values[1][0][0]) {
                hypotheses = Arrays.asList(negations.get(1), negations.get(2));
            } else if (!values[0][0][0]) {
                hypotheses = new ArrayList<>(negations);
            } else {
                throw new UnprovenExpressionException(expression);
            }
        }
        return createTheorem(expression, hypotheses);
    }

    private Proof generateProofWithoutHypotheses(Theorem theorem) {
        Logical expression = theorem.getExpression().clone();
        List<Variable> variables = new ArrayList<>(LogicUtil.collectVariables(theorem.getExpression()));
        List<ProofLine> lines = walkBinaryValues(expression, variables, 0, new HashMap<>());
        return new Proof(lines);
    }

    private Proof generateProofWithSimpleHypotheses(Theorem theorem) {
        Logical resultOfDeductionTheorem = theorem.getExpression();
        List<Logical> hypotheses = theorem.getHypotheses();
        for (Logical hypothesis : hypotheses) {
            resultOfDeductionTheorem = new Implication(hypothesis, resultOfDeductionTheorem);
        }

        Theorem additionalTheorem = createTheorem(resultOfDeductionTheorem);
        Proof additionalProof = generateProofWithoutHypotheses(additionalTheorem);
        List<ProofLine> lines = additionalProof.getLines();

        for (int i = 0; i < hypotheses.size(); i++) {
            Implication implication = (Implication) lines.get(lines.size() - 1).getExpression();
            lines.add(new ProofLine(implication.get(0)));
            lines.add(new ProofLine(implication.get(1)));
        }
        return new Proof(lines);
    }

    private List<ProofLine> walkBinaryValues(Logical expression, List<Variable> variables, int index, Map<String, Object> values) {
        if (index < variables.size()) {
            Variable v = variables.get(index);
            List<Logical> currentHypotheses = new ArrayList<>();
            for (int i = 0; i < index; i++) {
                if ((Boolean) values.get(variables.get(i).toString())) {
                    currentHypotheses.add(variables.get(i));
                } else {
                    currentHypotheses.add(new Negation(variables.get(i)));
                }
            }

            values.put(v.getName(), true);
            List<ProofLine> caseTrue = walkBinaryValues(expression, variables, index + 1, values);
            values.remove(v.getName(), true);

            values.put(v.getName(), false);
            List<ProofLine> caseFalse = walkBinaryValues(expression, variables, index + 1, values);
            values.remove(v.getName(), false);

            List<ProofLine> result = correct(v, caseTrue, currentHypotheses);
            result.addAll(correct(new Negation(v), caseFalse, currentHypotheses));
            result.addAll(Arrays.stream(aOrNotALemma.replace(createReplacing(v))).map(ProofLine::new).collect(Collectors.toList()));
            result.addAll(Arrays.stream(wowLemma.replace(createReplacing(expression, v))).map(ProofLine::new).collect(Collectors.toList()));
            return result;
        } else {
            return checkBinaryValues(expression, values);
        }
    }

    private List<ProofLine> checkBinaryValues(Logical expression, Map<String, Object> values) {
        List<ProofLine> lines = new ArrayList<>();
        List<String> forAdd = new ArrayList<>();

        if (expression instanceof Variable) {
            Variable variable = (Variable) expression;
            if (variable.evaluate(values)) {
                forAdd.add(variable.getName());
            } else {
                forAdd.add(new Negation(variable).toString());
            }
        } else if (expression instanceof Operation) {
            Operation operation = (Operation) expression;
            int n = operation.getArity();
            Boolean[] argumentValues = new Boolean[n];
            for (int i = 0; i < n; i++) {
                lines.addAll(checkBinaryValues(operation.get(i), values));
                argumentValues[i] = operation.get(i).evaluate(values);
            }
            if (operation instanceof Negation) {
                forAdd.addAll(Arrays.asList(LemmaUtil.negationReplace(operation.get(0), argumentValues[0])));
            } else if (operation instanceof Conjunction) {
                forAdd.addAll(Arrays.asList(LemmaUtil.conjunctionReplace(operation.get(0), argumentValues[0], operation.get(1), argumentValues[1])));
            } else if (operation instanceof Disjunction) {
                forAdd.addAll(Arrays.asList(LemmaUtil.disjunctionReplace(operation.get(0), argumentValues[0], operation.get(1), argumentValues[1])));
            } else if (operation instanceof Implication) {
                forAdd.addAll(Arrays.asList(LemmaUtil.implicationReplace(operation.get(0), argumentValues[0], operation.get(1), argumentValues[1])));
            }
        }
        lines.addAll(forAdd.stream().map(ProofLine::new).collect(Collectors.toList()));
        return lines;
    }

    private List<ProofLine> correct(Logical expression, List<ProofLine> lines, final List<Logical> hypotheses) {
        Set<Logical> checked = new HashSet<>();
        List<ProofLine> result = new ArrayList<>();

        Map<Logical, List<Logical>> implications = new HashMap<>();
        Map<Logical, Logical> reverseImplications = new HashMap<>();
        for (ProofLine line : lines) {
            Logical currentExpression = line.getExpression();
            if (currentExpression instanceof Implication) {
                Implication impl = (Implication) currentExpression;
                if (!checked.contains(impl.get(0))) {
                    implications.computeIfAbsent(impl.get(1), k -> new ArrayList<>());
                    implications.get(impl.get(1)).add(impl.get(0));
                } else {
                    implications.put(impl.get(1), null);
                    reverseImplications.put(impl.get(1), impl.get(0));
                }
            }
            if (isAxiom(line.getExpression()) || hypotheses.contains(line.getExpression())) {
                result.add(line);
                result.add(new ProofLine(new Implication(currentExpression, new Implication(expression, currentExpression))));
                result.add(new ProofLine(new Implication(expression, currentExpression)));
            } else if (currentExpression.equals(expression)) {
                result.addAll(Arrays.stream(aImplALemma.replace(createReplacing(currentExpression))).map(ProofLine::new).collect(Collectors.toList()));
            } else {
                Logical expr = null;
                if (reverseImplications.containsKey(currentExpression)) {
                    expr = reverseImplications.get(currentExpression);
                } else {
                    for (Logical currentExpr : implications.get(currentExpression)) {
                        if (checked.contains(currentExpr)) {
                            reverseImplications.put(currentExpression, currentExpr);
                            implications.put(currentExpression, null);
                            expr = currentExpr;
                            break;
                        }
                    }
                }
                Implication implication = new Implication(expression, currentExpression);
                result.add(new ProofLine(getAxioms()[1].implement(createMapping(expression, Objects.requireNonNull(expr), currentExpression))));
                result.add(new ProofLine(new Implication(new Implication(expression, new Implication(expr, currentExpression)), implication)));
                result.add(new ProofLine(implication));
            }
            checked.add(currentExpression);
        }
        return result;
    }
}
