package parser.tokenizer;

import exceptions.MissedClosingBraceException;
import exceptions.MissedOperandException;
import exceptions.MissedOperationException;
import exceptions.ParsingException;

@SuppressWarnings("WeakerAccess")
public
class Tokenizer {
    private String mExpression;
    private Token mCurrentToken;
    private int mCurrentPosition;
    private String mName;
    private int mBracesBalance;

    public Tokenizer() {
        this.mExpression = "";
        this.mCurrentToken = Token.START;
        this.mCurrentPosition = 0;
        this.mName = "";
    }

    public Tokenizer(String expression) {
        this.mExpression = expression;
        this.mCurrentToken = Token.START;
        this.mCurrentPosition = 0;
        this.mName = "";
    }

    public String getName() {
        return mName;
    }

    public Token getCurrentToken() {
        return mCurrentToken;
    }

    public void close() throws ParsingException {
        if (mBracesBalance > 0) {
            throw new MissedClosingBraceException(mCurrentPosition, mExpression);
        }
        checkForBinaryOperations();
        mCurrentToken = Token.END;
    }

    public final void nextToken() throws ParsingException {
        skipSpaces();
        if (!(isNotEnd())) {
            close();
            return;
        }
        char symbol = mExpression.charAt(mCurrentPosition);
        mCurrentPosition++;
        switch (symbol) {
            case '’':
                mCurrentToken = Token.INC;
                return;
            case ',':
                mCurrentToken = Token.COMMA;
                return;
            case '.':
                mCurrentToken = Token.DOT;
                return;
            case '@':
                mCurrentToken = Token.UN;
                return;
            case '?':
                mCurrentToken = Token.EX;
                return;
            case '&':
                checkForBinaryOperations();
                mCurrentToken = Token.AND;
                return;
            case '|':
                checkForBinaryOperations();
                mCurrentToken = Token.OR;
                return;
            case '!':
                checkForUnaryOperations();
                mCurrentToken = Token.NOT;
                return;
            case '→':
                checkForBinaryOperations();
                mCurrentToken = Token.IMPL;
                return;
            case '+':
                checkForBinaryOperations();
                mCurrentToken = Token.ADD;
                return;
            case '*':
                checkForBinaryOperations();
                mCurrentToken = Token.MUL;
                return;
            case '=':
                checkForBinaryOperations();
                mCurrentToken = Token.EQ;
                return;
            case '(':
                checkForOperands();
                mBracesBalance++;
                mCurrentToken = Token.OPENING_BRACE;
                return;
            case ')':
                checkForBinaryOperations();
                mBracesBalance--;
                if (mBracesBalance < 0) {
                    throw new MissedClosingBraceException(mCurrentPosition, mExpression);
                }
                if ((Token.isOperation(mCurrentToken) || mCurrentToken == Token.OPENING_BRACE) && mCurrentToken != Token.INC) {
                    throw new MissedOperationException(mCurrentPosition, mExpression);
                }
                mCurrentToken = Token.CLOSING_BRACE;
                return;
            default:
                mCurrentPosition--;
                mName = getWord();
                mCurrentToken = Token.VAR;
        }
    }

    private void skipSpaces() {
        while (isNotEnd() && Character.isWhitespace(mExpression.charAt(mCurrentPosition))) {
            mCurrentPosition++;
        }
    }

    private String getWord() throws ParsingException {
//        if (!Character.isLetter(mExpression.charAt(mCurrentPosition))) {
//            throw new UnknownSymbolException("" + mExpression.charAt(mCurrentPosition));
//        }
        int begin = mCurrentPosition;
        while (isNotEnd() && (Character.isLetterOrDigit(mExpression.charAt(mCurrentPosition)) || mExpression.charAt(mCurrentPosition) == '\'')) {
            mCurrentPosition++;
        }
        return mExpression.substring(begin, mCurrentPosition);
    }

    private void checkForBinaryOperations() throws MissedOperandException {
        if ((mCurrentToken == Token.OPENING_BRACE || Token.isOperation(mCurrentToken) || mCurrentToken == Token.START) && mCurrentToken != Token.INC) {
            throw new MissedOperandException(mCurrentPosition, mExpression);
        }
    }

    private void checkForOperands() throws MissedOperationException {
        if (mCurrentToken == Token.CLOSING_BRACE) {
            throw new MissedOperationException(mCurrentPosition, mExpression);
        }
    }

    private void checkForUnaryOperations() throws MissedOperandException {
        if (!(isNotEnd())) {
            throw new MissedOperandException(mCurrentPosition, mExpression);
        }
    }

    private boolean isNotEnd() {
        return (mCurrentPosition < mExpression.length());
    }

    public void apply(Backup backup) {
        this.mCurrentToken = backup.mCurrentToken;
        this.mCurrentPosition = backup.mCurrentPosition;
        this.mName = backup.mName;
        this.mBracesBalance = backup.mBracesBalance;
    }

    public Backup backup() {
        return new Backup(mCurrentToken, mCurrentPosition, mName, mBracesBalance);
    }

    public class Backup {
        private Token mCurrentToken;
        private int mCurrentPosition;
        private String mName;
        private int mBracesBalance;

        public Backup(Token mCurrentToken, int mCurrentPosition, String mName, int mBracesBalance) {
            this.mCurrentToken = mCurrentToken;
            this.mCurrentPosition = mCurrentPosition;
            this.mName = mName;
            this.mBracesBalance = mBracesBalance;
        }
    }
}
