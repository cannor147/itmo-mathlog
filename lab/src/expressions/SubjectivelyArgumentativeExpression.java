package expressions;

import expressions.util.Location;

public abstract class SubjectivelyArgumentativeExpression extends ArgumentativeExpression<Subjective> {
    private final Subjective[] arguments;

    public abstract String getName();
    public abstract boolean isConcrete();
    public abstract Location getLocation();

    public SubjectivelyArgumentativeExpression(Subjective... arguments) {
        this.arguments = arguments;
    }

    public int getArity() {
        return arguments.length;
    }

    public Subjective get(int index) {
        return arguments[index];
    }

    @SuppressWarnings("unused")
    public void change(int index, Subjective expression) {
        arguments[index] = expression;
    }
}
