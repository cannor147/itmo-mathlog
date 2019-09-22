package expressions.util;

public enum Location {
    PREFIX_EXTERN(false, true),
    PREFIX_WITHOUT_COMMA(false, false),
    PREFIX(true, false),
    INFIX(false, false),
    POSTFIX(true, false),
    POSTFIX_WITHOUT_COMMA(false, false),
    POSTFIX_EXTERN(false, true);

    private boolean needComma;
    private boolean extern;

    Location(boolean needComma, boolean extern) {
        this.needComma = needComma;
        this.extern = extern;
    }

    public boolean isNeedComma() {
        return needComma;
    }

    public boolean isExtern() {
        return extern;
    }

    public boolean isIntern() {
        return !extern;
    }

    public boolean isPrefix() {
        return this == PREFIX_EXTERN || this == PREFIX || this == PREFIX_WITHOUT_COMMA;
    }

    public boolean isPostfix() {
        return this == POSTFIX_EXTERN || this == POSTFIX || this == POSTFIX_WITHOUT_COMMA;
    }

    public boolean isInfix() {
        return this == INFIX;
    }
}
