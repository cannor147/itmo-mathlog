package my.theory;

import expressions.Logical;
import expressions.subject.SubjectVariable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PreAxiom {
    static final PreAxiom first = new PreAxiom("1", "(α→(β→α))");
    static final PreAxiom second = new PreAxiom("2", "((α→β)→((α→β→γ)→(α→γ)))");
    static final PreAxiom third = new PreAxiom("3", "(α→(β→(α&β)))");
    static final PreAxiom fourth = new PreAxiom("4", "((α&β)→α)");
    static final PreAxiom fifth = new PreAxiom("5", "((α&β)→β)");
    static final PreAxiom sixth = new PreAxiom("6", "(α→(α|β))");
    static final PreAxiom seventh = new PreAxiom("7", "(β→(α|β))");
    static final PreAxiom eighth = new PreAxiom("8", "((α→γ)→((β→γ)→((α|β)→γ)))");
    static final PreAxiom ninth = new PreAxiom("9", "((α→β)→((α→(!β))→(!α)))");
    static final PreAxiom tenth = new PreAxiom("10", "((!(!α))→α)");

    static final PreAxiom tenthIntuitionistic = new PreAxiom("10", "(α→((!α)→β))");

    static final PreAxiom firstPredicate = new PreAxiom("1P", "(@χ.α)→α", "α[χ]#1:χ", "α[χ]#2:*");
    static final PreAxiom secondPredicate = new PreAxiom("2P", "α→(?χ.α)", "α[χ]#1:*", "α[χ]#2:χ");

    static final PreAxiom firstArithmetic = new PreAxiom("1A", "(a=b)→(a’=b’)");
    static final PreAxiom secondArithmetic = new PreAxiom("2A", "(a=b)→((a=c)→(b=c))");
    static final PreAxiom thirdArithmetic = new PreAxiom("3A", "(a’=b’)→(a=b)");
    static final PreAxiom fourthArithmetic = new PreAxiom("4A", "!(a’=0)");
    static final PreAxiom fifthArithmetic = new PreAxiom("5A", "(a+b’)=(a+b)’");
    static final PreAxiom sixthArithmetic = new PreAxiom("6A", "(a+0)=a");
    static final PreAxiom seventhArithmetic = new PreAxiom("7A", "(a*0)=0");
    static final PreAxiom eighthArithmetic = new PreAxiom("8A", "(a*b’)=((a*b)+a)");
    static final PreAxiom ninthArithmetic = new PreAxiom("9A", "α&(@χ.(α→α))→α",
            "α[χ]#1:0", "α[χ]#2:χ", "α[χ]#3:χ’", "α[χ]#4:*");

    private final String name;
    private final String pattern;
    private final Map<Character, Class> metaVariables;
    private final Map<Character, Map<Character, Map<Integer, String>>> substitutions;

    PreAxiom(String name, String pattern, String... substitutions) {
        this.name = name;
        this.pattern = pattern;
        this.metaVariables = new HashMap<>();
        this.substitutions = new HashMap<>();

        int n = pattern.length();
        for (int i = 0; i < n; i++) {
            final char symbol = pattern.charAt(i);
            if (Arrays.asList(TheoryUtil.FIRST_GREEK_SYMBOLS).contains(symbol)) {
                metaVariables.put(symbol, Logical.class);
            }
            if (Arrays.asList(TheoryUtil.LAST_GREEK_SYMBOLS).contains(symbol)) {
                metaVariables.put(symbol, SubjectVariable.class);
            }
        }

        for (String substitution : substitutions) {
            String[] s = substitution.split(":");
            String firstPart = s[0];
            String secondPart = s.length > 1 ? s[1] : "*";
            if (firstPart.length() < 6 || firstPart.charAt(1) != '[' || firstPart.charAt(3) != ']' || firstPart.charAt(4) != '#') {
                throw new RuntimeException("kek");
            }
            char variable = firstPart.charAt(0);
            char subjectVariable = firstPart.charAt(2);
            if (!metaVariables.containsKey(variable) || metaVariables.get(variable) != Logical.class) {
                throw new RuntimeException("kek");
            }

            if (!this.substitutions.containsKey(variable)) {
                this.substitutions.put(variable, new HashMap<>());
            }
            if (!this.substitutions.get(variable).containsKey(subjectVariable)) {
                this.substitutions.get(variable).put(subjectVariable, new HashMap<>());
            }
            Integer x = Integer.valueOf(firstPart.substring(5));
            this.substitutions.get(variable).get(subjectVariable).put(x, secondPart);
        }
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }

    public Map<Character, Class> getMetaVariables() {
        return metaVariables;
    }

    public Map<Character, Map<Character, Map<Integer, String>>> getSubstitutions() {
        return substitutions;
    }
}
