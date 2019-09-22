package util;

public class StringUtil {
    @SuppressWarnings("StringRepeatCanBeUsed")
    public static String repeat(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
