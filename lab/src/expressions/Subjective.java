package expressions;

import java.util.Map;

public interface Subjective extends Expression {
    Object evaluate(Map<String, Object> values);
    Subjective clone();
}
