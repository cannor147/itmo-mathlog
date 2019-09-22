package my.theory;

import my.Theorem;
import my.Theory;
import parser.LogicalParser;

public class ArithmeticTheory extends Theory {
    ArithmeticTheory(LogicalParser parser, TheoryUtil.PreAxiom... axioms) {
        super(parser, axioms);
    }

    @Override
    public void generateProof(Theorem theorem) throws Exception {
        throw new Exception("kek");
    }
}
