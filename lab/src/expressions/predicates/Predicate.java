package expressions.predicates;

import expressions.Logical;
import expressions.Subjective;
import expressions.SubjectivelyArgumentativeExpression;
import expressions.util.Location;

import java.util.Map;

@SuppressWarnings("unused")
public class Predicate extends SubjectivelyArgumentativeExpression implements Logical {
    private final String name;
    private boolean concrete = false;

    public Predicate(String name, Subjective... arguments) {
        super(arguments);
        this.name = name;
    }

    protected Predicate(Subjective... arguments) {
        this(null, arguments);
        this.concrete = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final boolean isConcrete() {
        return concrete;
    }

    @Override
    public Location getLocation() {
        return Location.PREFIX_EXTERN;
    }

    @Override
    public Boolean evaluate(Map<String, Object> values) {
        throw new RuntimeException("Can't evaluate the predicate.");
    }

    @Override
    public Predicate clone() {
        int n = getArity();
        Subjective[] args = new Subjective[n];
        for (int i = 0; i < n; i++) {
            args[i] = get(i).clone();
        }
        return new Predicate(getName(), args);
    }
}