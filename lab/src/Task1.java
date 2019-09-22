import expressions.Logical;
import expressions.util.ExpressionUtil;

import java.util.Scanner;

import static my.theory.TheoryUtil.propositionalCalculus;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().replace("->", "→");

        Logical expression = propositionalCalculus.parse(str);
        System.out.println(ExpressionUtil.transformToNPN(expression).replace("→", "->"));
    }
}
