import expressions.Logical;
import my.Theorem;
import my.proofs.Proof;
import my.proofs.ProofLine;
import my.theory.FormalArithmetic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task5 {
    private enum Mode {
        OLD_TESTS,
        SIXTH_TASK,
        NONE
    }

    private static final Mode mode = Mode.NONE;
    private static final boolean write = false;
    private static final boolean checkHypothesesFreeVariable = false;

    private static String correctExpr(String expr) {
        String result =  expr.replace("->", "→").replace("'", "’");
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FormalArithmetic formalArithmetic = FormalArithmetic.getInstance();

        Theorem theorem;
        List<ProofLine> lines;
        Logical result;
        if (mode == Mode.SIXTH_TASK) {
            String[] v = reader.readLine().split(" ");
            int x = Integer.parseInt(v[0]);
            int y = Integer.parseInt(v[1]);
            Proof proof = Task6.getProof(x, y);
            lines = proof.getLines();
            result = lines.get(lines.size() - 1).getExpression();
            theorem = formalArithmetic.createTheorem(result);
            theorem.setProof(proof);
        } else {
            List<Logical> hypotheses = new ArrayList<>();
            String title = reader.readLine().replace("|-", "⊢");
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
            String str;
            while ((str = reader.readLine()) != null) {
                str = correctExpr(str);
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
