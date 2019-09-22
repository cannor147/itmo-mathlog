package parser.tokenizer;

@SuppressWarnings("RedundantIfStatement")
public
enum Token {
    VAR, //variable expression
    OPENING_BRACE, //opening brace
    CLOSING_BRACE, //closed brace

    IMPL,           // implication operation
    AND,            // conjunction operation
    OR,             // disjunction operation
    NOT,            // negation operation
    ADD,
    MUL,
    INC,
    EQ,

    UN,
    EX,
    DOT,
    COMMA,

    START, //start of parsing
    END; //end of parsing

    public static boolean isOperation(Token token) {
        if (isOperand(token) || isBrace(token) || isCommand(token) || isKek(token)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isKek(Token token) {
        return token == UN || token == EX || token == DOT || token == COMMA;
    }

    public static boolean isBrace(Token token) {
        if ((token == OPENING_BRACE) || (token == CLOSING_BRACE)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperand(Token token) {
        if (token == VAR) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCommand(Token token) {
        if ((token == START) || (token == END)) {
            return true;
        } else {
            return false;
        }
    }
}
