package expressions.subject;

import expressions.Subjective;
import expressions.SubjectivelyArgumentativeExpression;
import expressions.util.Location;

import java.util.Map;

@SuppressWarnings({"unused"})
public class Function extends SubjectivelyArgumentativeExpression implements Subjective {
    private final String name;
    private boolean concrete = false;

    public Function(String name, Subjective... arguments) {
        super(arguments);
        this.name = name;
    }

    protected Function(Subjective... arguments) {
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
    public Object evaluate(Map<String, Object> values) {
        throw new RuntimeException("Can't evaluate the function.");
    }

    @Override
    public Function clone() {
        int n = getArity();
        Subjective[] args = new Subjective[n];
        for (int i = 0; i < n; i++) {
            args[i] = get(i).clone();
        }
        return new Function(name, args);
    }
}
