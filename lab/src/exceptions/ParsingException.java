package exceptions;

import util.StringUtil;

public class ParsingException
        extends Exception {

    public ParsingException(final String message) {
        super(message);
    }

    @SuppressWarnings("SameParameterValue")
    static protected String getPlace(final int position, final int length) {
        return StringUtil.repeat(" ", Math.max(0, position)) + '^' + StringUtil.repeat("~", Math.max(0, length - 1));
    }
}
