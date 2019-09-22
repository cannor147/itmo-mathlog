package parser.impl;

import exceptions.ParsingException;
import expressions.Logical;
import expressions.operations.impl.Conjunction;
import expressions.operations.impl.Disjunction;
import expressions.operations.impl.Implication;
import expressions.operations.impl.Negation;
import expressions.predicates.Variable;
import parser.LogicalParser;
import parser.tokenizer.Tokenizer;

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class SimpleParser implements LogicalParser {

    private Tokenizer mTokenizer;

    public SimpleParser() {
        mTokenizer = new Tokenizer();
    }

    public Logical parse(final String expression) {
        try {
            mTokenizer = new Tokenizer(expression);
            return evaluating();
        } catch (ParsingException e) {
            return new Variable("PARSING_ERROR");
        }
    }

    private Logical evaluating() throws ParsingException {
        return implication();
    }

    private Logical implication() throws ParsingException {
        Logical result = disjunction();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case IMPL:
                    result = new Implication(result, evaluating());
                    break;
                default:
                    return result;
            }
        }
    }

    private Logical disjunction() throws ParsingException {
        Logical result = conjunction();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case OR:
                    result = new Disjunction(result, conjunction());
                    break;
                default:
                    return result;
            }
        }
    }

    private Logical conjunction() throws ParsingException {
        Logical result = unaryOperations();
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case AND:
                    result = new Conjunction(result, unaryOperations());
                    break;
                default:
                    return result;
            }
        }
    }

    private Logical unaryOperations() throws ParsingException {
        mTokenizer.nextToken();
        Logical result;
        switch (mTokenizer.getCurrentToken()) {
            case VAR:
                String name = mTokenizer.getName();
                result = new Variable(name);
                mTokenizer.nextToken();
                break;
            case NOT:
                result = new Negation(unaryOperations());
                break;
            case OPENING_BRACE:
                result = evaluating();
                mTokenizer.nextToken();
                break;
            default:
                throw new ParsingException("kek");
        }
        return result;
    }
}
