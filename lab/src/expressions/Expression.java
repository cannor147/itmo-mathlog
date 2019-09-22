package expressions;

import java.util.Map;

public interface Expression {
    Object evaluate(Map<String, Object> values);
    Expression clone();
}
