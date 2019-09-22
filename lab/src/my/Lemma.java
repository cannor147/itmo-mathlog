package my;

import java.util.Arrays;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Lemma {
    private final String name;
    private final String[] proof;

    public Lemma(String name, String[] proof) {
        this.name = name;
        this.proof = proof;
    }

    public Lemma(String[] proof) {
        this(proof[proof.length - 1], proof);
    }

    public String getName() {
        return name;
    }

    public String[] replace(Map<Character, String> metaReplacing) {
        return Arrays.stream(proof).map(proofLine -> replaceGreekSymbols(proofLine, metaReplacing)).toArray(String[]::new);
    }

    private static String replaceGreekSymbols(String original, Map<Character, String> metaReplacing) {
        String result = original;
        for (Map.Entry<Character, String> entry : metaReplacing.entrySet()) {
            result = result.replace("" + entry.getKey(), entry.getValue());
        }
        return result;
    }
}
