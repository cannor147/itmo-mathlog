package parser.impl;

import exceptions.ParsingException;
import expressions.Subjective;
import expressions.constants.Constant;
import expressions.subject.Function;
import expressions.subject.SubjectVariable;
import expressions.subject.impl.Addition;
import expressions.subject.impl.Incrementation;
import expressions.subject.impl.Multiplication;
import parser.SubjectiveParser;
import parser.tokenizer.Token;
import parser.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class ArithmeticalParser implements SubjectiveParser {
    public Subjective parse(final String expr) {
        try {
            Tokenizer mTokenizer = new Tokenizer(expr);
            return evaluating(mTokenizer);
        } catch (ParsingException e) {
            System.err.println(e.getMessage() + ": " + expr);
            return new SubjectVariable("PARSING_ERROR");
        }
    }

    Subjective helpParse(Tokenizer mTokenizer) throws ParsingException {
        return evaluating(mTokenizer);
    }

    private Subjective evaluating(Tokenizer mTokenizer) throws ParsingException {
        return addition(mTokenizer);
    }

    private Subjective addition(Tokenizer mTokenizer) throws ParsingException {
        Subjective result = multiplication(mTokenizer);
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case ADD:
                    result = new Addition(result, multiplication(mTokenizer));
                    break;
                default:
                    return result;
            }
        }
    }

    private Subjective multiplication(Tokenizer mTokenizer) throws ParsingException {
        Subjective result = unaryOperation(mTokenizer);
        while(true) {
            switch (mTokenizer.getCurrentToken()) {
                case MUL:
                    result = new Multiplication(result, unaryOperation(mTokenizer));
                    break;
                default:
                    return result;
            }
        }
    }

    private Subjective unaryOperation(Tokenizer mTokenizer) throws ParsingException {
        mTokenizer.nextToken();
        Subjective result;
        switch (mTokenizer.getCurrentToken()) {
            case VAR:
                String name = mTokenizer.getName();
                mTokenizer.nextToken();
                if (name.equals("0")) {
                    result = new Constant(0);
                } else if (mTokenizer.getCurrentToken() == Token.OPENING_BRACE) {
                    List<Subjective> arguments = new ArrayList<>();
                    arguments.add(addition(mTokenizer));
                    while (mTokenizer.getCurrentToken() != Token.CLOSING_BRACE) {
                        if (mTokenizer.getCurrentToken() == Token.COMMA) {
                            arguments.add(addition(mTokenizer));
                        } else {
                            throw new ParsingException("kek");
                        }
                    }
                    mTokenizer.nextToken();
                    result = new Function(name, arguments.toArray(new Subjective[0]));
                } else {
                    result = new SubjectVariable(name);
                }
                break;
            case OPENING_BRACE:
                result = addition(mTokenizer);
                mTokenizer.nextToken();
                break;
            default:
                throw new ParsingException("kek");
        }
        while (mTokenizer.getCurrentToken() == Token.INC) {
            mTokenizer.nextToken();
            result = new Incrementation(result);
        }
        return result;
    }
}
