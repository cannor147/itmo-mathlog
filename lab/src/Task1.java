import expressions.Logical;
import expressions.util.ExpressionUtil;
import my.theory.PropositionalCalculus;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PropositionalCalculus propositionalCalculus = PropositionalCalculus.getInstance();
        String str = scanner.nextLine().replace("->", "→");

        Logical expression = propositionalCalculus.parse(str);
        System.out.println(ExpressionUtil.transformToNPN(expression).replace("→", "->"));
    }
}
