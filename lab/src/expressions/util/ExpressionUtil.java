package expressions.util;

import expressions.AbstractExpression;
import expressions.ArgumentativeExpression;
import expressions.Expression;
import expressions.constants.Constant;
import expressions.predicates.Variable;
import expressions.quantifier.Quantifier;
import expressions.subject.SubjectVariable;

public class ExpressionUtil {
    public static String transformToNPN(Expression expression) {
        return smartTransform(expression, 1, true);
    }

    public static String transform(Expression expression) {
        return smartTransform(expression, 0, false);
    }

    public static boolean isInstanceOf(Expression expression, Class<?> clazz) {
        Class current = expression.getClass();
        boolean result = false;
        while (current != Object.class && current != AbstractExpression.class) {
            result = current == clazz;
            for (Class anInterface : current.getInterfaces()) {
                result |= anInterface == clazz;
            }
            if (result) {
                return true;
            }
            current = current.getSuperclass();
        }
        return false;
    }

    private static String smartTransform(Expression expression, int mode, boolean braces) {
        if (expression instanceof ArgumentativeExpression) {
            String name = ((ArgumentativeExpression) expression).getName();
            int n = ((ArgumentativeExpression) expression).getArity();
            Location location = ((ArgumentativeExpression) expression).getLocation();
            String[] args = new String[n];
            for (int i = 0; i < n; i++) {
                args[i] = smartTransform(((ArgumentativeExpression) expression).get(i), mode, braces);
            }

            if (mode == 1) {
                location = location.isInfix() ? Location.PREFIX : location.isNeedComma() ? Location.PREFIX : Location.PREFIX_WITHOUT_COMMA;
            } else if (mode == 2) {
                location = location.isInfix() ? Location.POSTFIX : location.isNeedComma() ? Location.POSTFIX : Location.POSTFIX_WITHOUT_COMMA;
            }

            if (location.isInfix() && n != 2) {
                throw new RuntimeException("kek");
            }
            String result = "";
            if (location.isExtern() && location.isPrefix()) {
                result += name;
            }
            result += (!braces && ((ArgumentativeExpression) expression).isConcrete() && n == 1) ? "" : "(";
            if (location.isIntern() && location.isPrefix()) {
                result += name;
                if (location.isNeedComma()) {
                    result += ",";
                }
            }
            result += transformToList(location.isInfix() ? name : ",", args);
            if (location.isIntern() && location.isPostfix()) {
                if (location.isNeedComma()) {
                    result += ",";
                }
                result += name;
            }
            result += (!braces && ((ArgumentativeExpression) expression).isConcrete() && n == 1) ? "" : ")";
            if (location.isExtern() && location.isPostfix()) {
                result += name;
            }
            return result;
        } else if (expression instanceof Quantifier) {
            return "(" + ((Quantifier) expression).getName() +
                    smartTransform(((Quantifier) expression).getVariable(), mode, braces) + ".(" +
                    smartTransform(((Quantifier) expression).getExpression(), mode, braces) + "))";
        } else if (expression instanceof Variable) {
            return ((Variable) expression).getName();
        } else if (expression instanceof SubjectVariable) {
            return ((SubjectVariable) expression).getName();
        } else if (expression instanceof Constant) {
            return ((Constant) expression).getValue().toString();
        } else {
            throw new RuntimeException("kek");
        }
    }

    private static String transformToList(String spliterator, String... exprs) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (String expr : exprs) {
            if (!first) {
                stringBuilder.append(spliterator);
            }
            stringBuilder.append(expr);
            first = false;
        }
        return stringBuilder.toString();
    }
}
