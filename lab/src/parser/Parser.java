package parser;

import expressions.Expression;

public interface Parser {
    Expression parse(final String expression);
}
