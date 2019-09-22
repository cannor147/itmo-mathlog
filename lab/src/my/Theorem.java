package my;

import expressions.Expression;
import expressions.Logical;
import expressions.operations.impl.Implication;
import expressions.quantifier.impl.ExistentialQuantifier;
import expressions.quantifier.impl.UniversalQuantifier;
import expressions.subject.SubjectVariable;
import expressions.util.ExpressionUtil;
import my.proofs.Annotation;
import my.proofs.Proof;
import my.proofs.ProofLine;
import my.util.LogicUtil;

import java.util.*;

public class Theorem {
    private final Theory theory;
    private final List<Logical> hypotheses;
    private final Logical expression;
    private Proof proof;
    private Annotation[] annotations;
    private String descriptionPattern;
    private boolean checkHypothesesFreeVariable;

    Theorem(Theory theory, Logical expression, List<Logical> hypotheses) {
        this.theory = theory;
        this.hypotheses = hypotheses;
        this.expression = expression;
        this.proof = null;
        this.annotations = null;
        this.descriptionPattern = "";
        this.checkHypothesesFreeVariable = false;
    }

    public void setCheckHypothesesFreeVariable(boolean checkHypothesesFreeVariable) {
        this.checkHypothesesFreeVariable = checkHypothesesFreeVariable;
    }

    private void generateAnnotations() {
        List<ProofLine> lines = proof.getLines();
        this.annotations = new Annotation[lines.size()];

        Map<String, Integer> used = new TreeMap<>();
        Map<String, List<Integer>> implicationRightParts = new TreeMap<>();
        Set<SubjectVariable> hypothesesFreeVariables = LogicUtil.collectFreeVariables(hypotheses.toArray(new Logical[0]));
        for (int index = 0; index < lines.size(); index++) {
            Logical currentExpression = lines.get(index).getExpression();
            boolean annotated = false;

            Axiom axiom = theory.checkForAxiom(currentExpression);
            if (axiom != null) {
                annotations[index] = new Annotation(axiom);
                annotated = true;
            }

            if (!annotated) {
                for (int i = 0; i < hypotheses.size(); i++) {
                    if (hypotheses.get(i).equals(currentExpression)) {
                        annotations[index] = new Annotation(i, expression);
                        annotated = true;
                        break;
                    }
                }
            }

            if (!annotated && implicationRightParts.containsKey(currentExpression.toString())) {
                List<Integer> usages = implicationRightParts.get(currentExpression.toString());
                for (Integer i : usages) {
                    if (ExpressionUtil.isInstanceOf(lines.get(i).getExpression(), Implication.class)) {
                        Implication impl = (Implication) lines.get(i).getExpression();
                        if (impl.get(1).equals(currentExpression)) {
                            Integer j = used.get(impl.get(0).toString());
                            if (j != null) {
                                annotations[index] = new Annotation(Annotation.Rule.MODUS_PONENS, i, j);
                                annotated = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!annotated && currentExpression instanceof Implication) {
                Implication impl = (Implication) currentExpression;
                if (impl.get(0) instanceof ExistentialQuantifier
                        && (!checkHypothesesFreeVariable || !hypothesesFreeVariables.contains(((ExistentialQuantifier) impl.get(0)).getVariable()))
                        && !LogicUtil.collectFreeVariables(impl.get(1)).contains(((ExistentialQuantifier) impl.get(0)).getVariable())) {
                    Expression temp = new Implication(((ExistentialQuantifier) impl.get(0)).getExpression(), impl.get(1));
                    Integer j = used.get(temp.toString());
                    if (j != null) {
                        annotations[index] = new Annotation(Annotation.Rule.PREDICATE_SECOND_RULE, j);
                        annotated = true;
                    }
                }
                if (!annotated && impl.get(1) instanceof UniversalQuantifier
                        && (!checkHypothesesFreeVariable || !hypothesesFreeVariables.contains(((UniversalQuantifier) impl.get(1)).getVariable()))
                        && !LogicUtil.collectFreeVariables(impl.get(0)).contains(((UniversalQuantifier) impl.get(1)).getVariable())) {
                    Expression temp = new Implication(impl.get(0), ((UniversalQuantifier) impl.get(1)).getExpression());
                    Integer j = used.get(temp.toString());
                    if (j != null) {
                        annotations[index] = new Annotation(Annotation.Rule.PREDICATE_FIRST_RULE, j);
                        annotated = true;
                    }
                }
            }

            if (!annotated) {
                annotations[index] = new Annotation();
            }

            if (!used.containsKey(currentExpression.toString())) {
                used.put(currentExpression.toString(), index);
            }
            if (currentExpression instanceof Implication) {
                Logical rightPart = ((Implication) currentExpression).get(1);
                if (!implicationRightParts.containsKey(rightPart.toString())) {
                    implicationRightParts.put(rightPart.toString(), new ArrayList<>(Collections.singleton(index)));
                } else {
                    implicationRightParts.get(rightPart.toString()).add(index);
                }
            }
        }
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
        this.annotations = null;
    }

    public boolean checkProof() {
        return findError() == 0;
    }

    public int findError() {
        int n = proof.getLines().size();
        Logical lastExpression = proof.getLines().get(proof.getLines().size() - 1).getExpression();
        if (!lastExpression.equals(expression)) {
            return n;
        }
        if (annotations == null) {
            generateAnnotations();
        }
        for (int i = 0; i < n; i++) {
            if (annotations[i].hasError()) {
                return i + 1;
            }
        }
        return 0;
    }

    public void minimizeProof() {
        List<ProofLine> lines = proof.getLines();
        if (annotations == null) {
            generateAnnotations();
        }

        boolean[] necessaryLine = new boolean[lines.size()];
        necessaryLine[lines.size() - 1] = true;

        for (int i = lines.size() - 1; i >= 0; i--) {
            if (!necessaryLine[i]) {
                continue;
            }

            int[] links = annotations[i].findLineIndexes();
            for (int link : links) {
                necessaryLine[link] = true;
            }
        }

        Integer[] indexMapping = new Integer[lines.size()];
        List<ProofLine> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (necessaryLine[i]) {
                indexMapping[i] = result.size();
                result.add(lines.get(i));
            }
        }

        Annotation[] newAnnotations = new Annotation[result.size()];
        int index = 0;
        for (int i = 0; i < annotations.length; i++) {
            if (necessaryLine[i]) {
                annotations[i].changeIndexes(indexMapping);
                newAnnotations[index] = annotations[i];
                index++;
            }
        }

        proof = new Proof(result);
        annotations = newAnnotations;
    }

    public Logical getExpression() {
        return expression;
    }

    public List<Logical> getHypotheses() {
        return hypotheses;
    }

    public boolean isHypothesis(Expression expression) {
        return expression instanceof Logical && hypotheses.contains(expression);
    }

    public ProofLine findModusPonens(int index) {
        List<ProofLine> lines = proof.getLines();
        ProofLine line = lines.get(index);

        for (int i = 0; i < index; i++) {
            if (!ExpressionUtil.isInstanceOf(lines.get(i).getExpression(), Implication.class)) {
                continue;
            }
            Implication impl = (Implication) lines.get(i).getExpression();
            if (impl.get(1).equals(line.getExpression())) {
                for (int j = 0; j < index; j++) {
                    if (impl.get(0).equals(lines.get(j).getExpression())) {
                        return lines.get(i);
                    }
                }
            }
        }
        return null;
    }

    public boolean isModusPonens(int index) {
        return findModusPonens(index) != null;
    }

    public void setDescriptionPattern(String descriptionPattern) {
        this.descriptionPattern = descriptionPattern.trim();
        if (!"".equals(descriptionPattern) && this.descriptionPattern.charAt(this.descriptionPattern.length() - 1) != ' ') {
            this.descriptionPattern += ' ';
        }
    }

    public final void specialWrite() {
        if (descriptionPattern.contains("?") && annotations == null) {
            generateAnnotations();
        }

        for (int i = 0; i < hypotheses.size(); i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(hypotheses.get(i).toString().replace("→", "->"));
        }
        if (hypotheses.size() != 0) {
            System.out.print(" ");
        }
        System.out.print("|- ");
        System.out.println(expression.toString().replace("→", "->"));

        List<ProofLine> lines = proof.getLines();
        for (int i = 0; i < lines.size(); i++) {
            if (i != 0) {
                System.out.println();
            }
            System.out.print(getDescription(i));
            System.out.print(lines.get(i).toString().replace("→", "->"));
        }
    }

    public final String toString() {
        if (descriptionPattern.contains("?") && annotations == null) {
            generateAnnotations();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hypotheses.size(); i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(hypotheses.get(i).toString());
        }
        if (hypotheses.size() != 0) {
            stringBuilder.append(" ");
        }
        stringBuilder.append("⊢ ");
        stringBuilder.append(expression.toString()).append(System.lineSeparator());

        List<ProofLine> lines = proof.getLines();
        for (int i = 0; i < lines.size(); i++) {
            if (i != 0) {
                stringBuilder.append(System.lineSeparator());
            }
            stringBuilder.append(getDescription(i));
            stringBuilder.append(lines.get(i).toString());
        }

        return stringBuilder.toString();
    }

    private String getDescription(int index) {
        String result = descriptionPattern;
        if (descriptionPattern.contains("#")) {
            result = result.replace("#", Integer.toString(index + 1));
        }
        if (descriptionPattern.contains("?")) {
            result = result.replace("?", annotations[index].toString());
        }
        return result;
    }
}