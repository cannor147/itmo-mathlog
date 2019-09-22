package exceptions;

public class MissedOperandException
        extends ParsingException {

    public MissedOperandException(int position, String expression) {
        super("missed operand before position: " + position + "\n" + expression + "\n" + getPlace(position, 1));
    }
}
