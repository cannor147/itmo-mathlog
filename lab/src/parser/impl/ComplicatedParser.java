package parser.impl;

import exceptions.ParsingException;
import expressions.Logical;
import expressions.Subjective;
import expressions.operations.impl.Conjunction;
import expressions.operations.impl.Disjunction;
import expressions.operations.impl.Implication;
import expressions.operations.impl.Negation;
import expressions.predicates.Predicate;
import expressions.predicates.Variable;
import expressions.predicates.impl.Equation;
import expressions.quantifier.impl.ExistentialQuantifier;
import expressions.quantifier.impl.UniversalQuantifier;
import expressions.subject.SubjectVariable;
import parser.LogicalParser;
import parser.tokenizer.Token;
import parser.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class ComplicatedParser implements LogicalParser {
    private static final ArithmeticalParser arithmeticParser = new ArithmeticalParser();
    
    private Tokenizer mTokenizer;

    public Logical parse(final String expr) {
        try {
            mTokenizer = new Tokenizer(expr);
            return evaluating();
        } catch (ParsingException e) {
            System.err.println(e.getMessage() + ": " + expr);
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
        Tokenizer.Backup backup = mTokenizer.backup();
        mTokenizer.nextToken();
        Logical result;
        switch (mTokenizer.getCurrentToken()) {
            case NOT:
                result = new Negation(unaryOperations());
                break;
            case OPENING_BRACE:
                try {
                    result = evaluating();
                    mTokenizer.nextToken();
                } catch (Exception e) {
                    result = predicate(backup);
                }
                break;
            case UN:
                result = quantifier(true);
                break;
            case EX:
                result = quantifier(false);
                break;
            default:
                result = predicate(backup);
        }
        return result;
    }

    private Logical predicate(Tokenizer.Backup backup) throws ParsingException {
        String name = mTokenizer.getName();
        if (mTokenizer.getCurrentToken() == Token.VAR && (Character.isUpperCase(name.charAt(0)) || name.charAt(0) >= 'α' && name.charAt(0) <= 'γ')) {
            mTokenizer.nextToken();

            if (mTokenizer.getCurrentToken() == Token.OPENING_BRACE) {
                List<Subjective> arguments = new ArrayList<>();
                arguments.add(arithmeticParser.helpParse(mTokenizer));
                while (mTokenizer.getCurrentToken() != Token.CLOSING_BRACE) {
                    if (mTokenizer.getCurrentToken() == Token.COMMA) {
                        arguments.add(arithmeticParser.helpParse(mTokenizer));
                    } else {
                        throw new ParsingException("kek");
                    }
                }
                mTokenizer.nextToken();
                return new Predicate(name, arguments.toArray(new Subjective[0]));
            } else {
                return new Variable(name);
            }
        } else {
            mTokenizer.apply(backup);
            return equation();
        }
    }

    private Logical quantifier(boolean un) throws ParsingException {
        mTokenizer.nextToken();
        if (mTokenizer.getCurrentToken() != Token.VAR) {
            throw new ParsingException("kek");
        }
        SubjectVariable variable = new SubjectVariable(mTokenizer.getName());

        mTokenizer.nextToken();
        if (mTokenizer.getCurrentToken() != Token.DOT) {
            throw new ParsingException("kek");
        }

        Logical expression = unaryOperations();
//        Logical expression = conjunction();
        return (un) ? new UniversalQuantifier(variable, expression) : new ExistentialQuantifier(variable, expression);
    }

    private Logical equation() throws ParsingException {
        Subjective result = arithmeticParser.helpParse(mTokenizer);
        if (mTokenizer.getCurrentToken() == Token.EQ) {
            return new Equation(result, arithmeticParser.helpParse(mTokenizer));
        } else {
            throw new ParsingException("kek");
        }

    }
}
