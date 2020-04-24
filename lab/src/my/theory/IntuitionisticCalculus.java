package my.theory;

import my.Theorem;
import my.Theory;
import parser.LogicalParser;
import parser.impl.SimpleParser;

import static my.theory.PreAxiom.*;

public class IntuitionisticCalculus extends Theory {
    private static IntuitionisticCalculus INSTANCE;

    public static IntuitionisticCalculus getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IntuitionisticCalculus(new SimpleParser(),
                    first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenthIntuitionistic);
        }
        return INSTANCE;
    }

    private IntuitionisticCalculus(LogicalParser parser, PreAxiom... axioms) {
        super(parser, axioms);
    }

    @Override
    public void generateProof(Theorem theorem) throws Exception {
        throw new Exception("kek");
    }
}

