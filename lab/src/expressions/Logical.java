package expressions;

import java.util.Map;

public interface Logical extends Expression {
    Boolean evaluate(Map<String, Object> values);
    Logical clone();
}
