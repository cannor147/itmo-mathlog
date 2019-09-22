import expressions.Logical;
import my.Theorem;

import java.util.Scanner;

import static my.theory.TheoryUtil.propositionalCalculus;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().replace("->", "→");

        Logical expression = propositionalCalculus.parse(str);
        try {
            Theorem theorem = propositionalCalculus.findMinimumHypotheses(expression);
            propositionalCalculus.generateProof(theorem);
            theorem.setDescriptionPattern("#. ");
            theorem.specialWrite();
        } catch (Exception e) {
            System.out.println(":(");
        }
    }
}
