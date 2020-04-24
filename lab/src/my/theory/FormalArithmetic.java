package my.theory;

import my.Theorem;
import my.Theory;
import parser.LogicalParser;
import parser.impl.ComplicatedParser;

import static my.theory.PreAxiom.*;

public class FormalArithmetic extends Theory {
    private static FormalArithmetic INSTANCE;

    public static FormalArithmetic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FormalArithmetic(new ComplicatedParser(),
                    first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth,
                    firstPredicate, secondPredicate,
                    firstArithmetic, secondArithmetic, thirdArithmetic, fourthArithmetic, fifthArithmetic,
                    sixthArithmetic, seventhArithmetic, eighthArithmetic, ninthArithmetic);
        }
        return INSTANCE;
    }

    private FormalArithmetic(LogicalParser parser, PreAxiom... axioms) {
        super(parser, axioms);
    }

    @Override
    public void generateProof(Theorem theorem) throws Exception {
        throw new Exception("kek");
    }
}
