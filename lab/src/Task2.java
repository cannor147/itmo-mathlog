import expressions.Logical;
import my.Theorem;
import my.proofs.Proof;
import my.proofs.ProofLine;
import my.theory.PropositionalCalculus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PropositionalCalculus propositionalCalculus = PropositionalCalculus.getInstance();

        List<Logical> hypotheses = new ArrayList<>();
        String title = scanner.nextLine().replace("->", "→").replace("|-", "⊢");
        String[] header = title.split("⊢");
        if (!header[0].equals("")) {
            hypotheses = Arrays.stream(header[0].split(",")).map(propositionalCalculus::parse).collect(Collectors.toList());
        }
        Logical result = propositionalCalculus.parse(header[1]);
        Theorem theorem = propositionalCalculus.createTheorem(result, hypotheses);

        List<ProofLine> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine().replace("->", "→");
            lines.add(new ProofLine(propositionalCalculus.parse(str)));
        }
        theorem.setProof(new Proof(lines));

        if (theorem.checkProof()) {
            theorem.minimizeProof();
            theorem.setDescriptionPattern("[#. ?]");
            theorem.specialWrite();
        } else {
            System.out.println("Proof is incorrect");
        }
    }
}
