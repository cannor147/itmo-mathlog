package parser;

import expressions.Logical;

public interface LogicalParser extends Parser {
    Logical parse(final String expression);
}
