package evaluators;

public class Evaluators {
    public static LogicEvaluator conjunction = new LogicEvaluator("&", args -> args[0] && args[1]);
    public static LogicEvaluator disjunction = new LogicEvaluator("|", args -> args[0] || args[1]);
    public static LogicEvaluator implication = new LogicEvaluator("→", args -> !args[0] || args[1]);
    public static LogicEvaluator negation = new LogicEvaluator("!", args -> !args[0]);

    public static ArithmeticEvaluator addition = new ArithmeticEvaluator("+", args -> args[0] + args[1]);
    public static ArithmeticEvaluator incrementation = new ArithmeticEvaluator("’", args -> args[0] + 1);
    public static ArithmeticEvaluator multiplication = new ArithmeticEvaluator("*", args -> args[0] * args[1]);

    public static PredicateEvaluator equality = new PredicateEvaluator("=", args -> args[0].equals(args[1]));
}
