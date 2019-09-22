import expressions.Logical;
import my.Theorem;
import my.proofs.Proof;
import my.proofs.ProofLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static my.theory.TheoryUtil.formalArithmetic;

public class Task5 {
    private enum Mode {
        OLD_TESTS,
        SIXTH_TASK,
        NONE
    }

    private static final Mode mode = Mode.SIXTH_TASK;
    private static final boolean write = true;
    private static final boolean checkHypothesesFreeVariable = false;

    private static String correctExpr(String expr) {
        String result =  expr.replace("->", "→");
        if (mode == Mode.OLD_TESTS) {
            StringBuilder lol = new StringBuilder();
            boolean quantified = false;
            for (int i = 0; i < result.length(); i++) {
                if (quantified && !(Character.isLowerCase(result.charAt(i)) || Character.isDigit(result.charAt(i)))) {
                    lol.append('.');
                    quantified = false;
                }
                if ((result.charAt(i) == '@') || (result.charAt(i) == '?')) {
                    quantified = true;
                }
                if (result.charAt(i) == '\'') {
                    lol.append('’');
                } else {
                    lol.append(result.charAt(i));
                }
            }
            result = lol.toString();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Theorem theorem;
        List<ProofLine> lines;
        Logical result;
        if (mode == Mode.SIXTH_TASK) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Proof proof = Task6.getProof(x, y);
            lines = proof.getLines();
            result = lines.get(lines.size() - 1).getExpression();
            theorem = formalArithmetic.createTheorem(result);
            theorem.setProof(proof);
        } else {
            List<Logical> hypotheses = new ArrayList<>();
            String title = scanner.nextLine().replace("|-", "⊢");
            String[] header = title.split("⊢");

            int braceBalance = 0;
            for (int i = 0; i < header[0].length(); i++) {
                if (header[0].charAt(i) == '(') {
                    braceBalance++;
                }
                if (header[0].charAt(i) == ')') {
                    braceBalance--;
                }
                if (header[0].charAt(i) == ',' && braceBalance == 0) {
                    StringBuilder tmp = new StringBuilder(header[0]);
                    tmp.setCharAt(i, ';');
                    header[0] = tmp.toString();
                }
            }

            if (!header[0].equals("")) {
                hypotheses = Arrays.stream(header[0].split(";")).map(Task5::correctExpr).map(formalArithmetic::parse).collect(Collectors.toList());
            }
            result = formalArithmetic.parse(correctExpr(header[1]));
            theorem = formalArithmetic.createTheorem(result, hypotheses);

            lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String str = correctExpr(scanner.nextLine());
                lines.add(new ProofLine(formalArithmetic.parse(str)));
            }
            theorem.setProof(new Proof(lines));
        }

        theorem.setCheckHypothesesFreeVariable(checkHypothesesFreeVariable);
        if (write) {
            theorem.setDescriptionPattern("[#. ?]");
            theorem.specialWrite();
            System.out.println();
        }
        if (theorem.checkProof()) {
            System.out.println("Proof is correct");
        } else {
            int k = theorem.findError();
            if (k == lines.size() && !lines.get(k - 1).getExpression().equals(result)) {
                System.out.println("Required hasn't been proven");
            } else {
                System.out.println("Line #" + k + " can't be obtained");
            }
        }
    }
}
