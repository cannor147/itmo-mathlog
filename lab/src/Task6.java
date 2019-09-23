import expressions.Logical;
import my.proofs.Proof;
import my.proofs.ProofLine;
import my.theory.ArithmeticTheory;
import my.util.LemmaUtil;
import my.util.LogicUtil;
import util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static my.theory.TheoryUtil.formalArithmetic;

public class Task6 {
    private static final String[] arithmeticHelper = {
            // first
            "(a=b)→(a’=b’)",
            "((a=b)→(a’=b’))→((0=0)→(0=0)→(0=0))→((a=b)→(a’=b’))",
            "((0=0)→(0=0)→(0=0))→((a=b)→(a’=b’))",
            "((0=0)→(0=0)→(0=0))→@a.((a=b)→(a’=b’))",
            "((0=0)→(0=0)→(0=0))→@b.@a.((a=b)→(a’=b’))",
            "(0=0)→(0=0)→(0=0)",
            "@b.@a.((a=b)→(a’=b’))",

            // second
            "(a=b)→(a=c)→(b=c)",
            "((a=b)→(a=c)→(b=c))→((0=0)→(0=0)→(0=0))→((a=b)→(a=c)→(b=c))",
            "((0=0)→(0=0)→(0=0))→((a=b)→(a=c)→(b=c))",
            "((0=0)→(0=0)→(0=0))→@a.((a=b)→(a=c)→(b=c))",
            "((0=0)→(0=0)→(0=0))→@b.@a.((a=b)→(a=c)→(b=c))",
            "((0=0)→(0=0)→(0=0))→@c.@b.@a.((a=b)→(a=c)→(b=c))",
            "(0=0)→(0=0)→(0=0)",
            "@c.@b.@a.((a=b)→(a=c)→(b=c))",

            // third
            "(a’=b’)→(a=b)",
            "((a’=b’)→(a=b))→((0=0)→(0=0)→(0=0))→((a’=b’)→(a=b))",
            "((0=0)→(0=0)→(0=0))→((a’=b’)→(a=b))",
            "((0=0)→(0=0)→(0=0))→@a.((a’=b’)→(a=b))",
            "((0=0)→(0=0)→(0=0))→@b.@a.((a’=b’)→(a=b))",
            "(0=0)→(0=0)→(0=0)",
            "@b.@a.((a’=b’)→(a=b))",

            // fourth
            "!(a’=0)",
            "!(a’=0)→((0=0)→(0=0)→(0=0))→!(a’=0)",
            "((0=0)→(0=0)→(0=0))→!(a’=0)",
            "((0=0)→(0=0)→(0=0))→@a.!(a’=0)",
            "(0=0)→(0=0)→(0=0)",
            "@a.!(a’=0)",

            // fifth
            "(a+b’)=(a+b)’",
            "((a+b’)=(a+b)’)→((0=0)→(0=0)→(0=0))→((a+b’)=(a+b)’)",
            "((0=0)→(0=0)→(0=0))→((a+b’)=(a+b)’)",
            "((0=0)→(0=0)→(0=0))→@a.((a+b’)=(a+b)’)",
            "((0=0)→(0=0)→(0=0))→@b.@a.((a+b’)=(a+b)’)",
            "(0=0)→(0=0)→(0=0)",
            "@b.@a.((a+b’)=(a+b)’)",

            // sixth
            "a+0=a",
            "(a+0=a)→((0=0)→(0=0)→(0=0))→(a+0=a)",
            "((0=0)→(0=0)→(0=0))→(a+0=a)",
            "((0=0)→(0=0)→(0=0))→@a.(a+0=a)",
            "(0=0)→(0=0)→(0=0)",
            "@a.(a+0=a)",

            // seventh
            "a*0=0",
            "(a*0=0)→((0=0)→(0=0)→(0=0))→(a*0=0)",
            "((0=0)→(0=0)→(0=0))→(a*0=0)",
            "((0=0)→(0=0)→(0=0))→@a.(a*0=0)",
            "(0=0)→(0=0)→(0=0)",
            "@a.(a*0=0)",

            // eighth
            "(a*b’)=((a*b)+a)",
            "((a*b’)=((a*b)+a))→((0=0)→(0=0)→(0=0))→((a*b’)=((a*b)+a))",
            "((0=0)→(0=0)→(0=0))→((a*b’)=((a*b)+a))",
            "((0=0)→(0=0)→(0=0))→@a.((a*b’)=((a*b)+a))",
            "((0=0)→(0=0)→(0=0))→@b.@a.((a*b’)=((a*b)+a))",
            "(0=0)→(0=0)→(0=0)",
            "@b.@a.((a*b’)=((a*b)+a))",
    };

    private static final String[] eq = {
            "@c.@b.@a.((a=b)→(a=c)→(b=c))→@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))→@a.((a=λ)→(a=λ)→(λ=λ))",
            "@a.((a=λ)→(a=λ)→(λ=λ))",
            "@a.((a=λ)→(a=λ)→(λ=λ))→((λ+0=λ)→(λ+0=λ)→(λ=λ))",
            "(λ+0=λ)→(λ+0=λ)→(λ=λ)",
            "(λ+0=λ)→(λ=λ)",
            "@a.(a+0=a)→(λ+0=λ)",
            "λ+0=λ",
            "λ=λ"
    };

    private static final String[] swap = {
            "@c.@b.@a.((a=b)→(a=c)→(b=c))→@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))→@a.((a=μ)→(a=λ)→(μ=λ))",
            "@a.((a=μ)→(a=λ)→(μ=λ))",
            "@a.((a=μ)→(a=λ)→(μ=λ))→((λ=μ)→(λ=λ)→(μ=λ))",
            "(λ=μ)→(λ=λ)→(μ=λ)",
            "(λ=λ)→(μ=λ)",
            "@a.(a+0=a)→(λ+0=λ)",
            "λ+0=λ",
            "@c.@b.@a.((a=b)→(a=c)→(b=c))→@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))",
            "@b.@a.((a=b)→(a=λ)→(b=λ))→@a.((a=λ)→(a=λ)→(λ=λ))",
            "@a.((a=λ)→(a=λ)→(λ=λ))",
            "@a.((a=λ)→(a=λ)→(λ=λ))→((λ+0=λ)→(λ+0=λ)→(λ=λ))",
            "(λ+0=λ)→(λ+0=λ)→(λ=λ)",
            "(λ+0=λ)→(λ=λ)",
            "λ=λ",
            "μ=λ"
    };

    private static final String[] contraposition = {
            "(α→β)→(α→!β)→!α",
            "(α→!β)→!α",
            "!β→α→!β",
            "((α→!β)→!α)→!β→((α→!β)→!α)",
            "!β→((α→!β)→!α)",
            "(!β→α→!β)→(!β→((α→!β)→!α))→(!β→!α)",
            "(!β→((α→!β)→!α))→(!β→!α)",
            "!β→!α"
    };

    private static final String[] secondArithmeticAxiomImpl = {
            "@c.@b.@a.((a=b)→(a=c)→(b=c))→@b.@a.((a=b)→(a=ν)→(b=ν))",
            "@b.@a.((a=b)→(a=ν)→(b=ν))",
            "@b.@a.((a=b)→(a=ν)→(b=ν))→@a.((a=μ)→(a=ν)→(μ=ν))",
            "@a.((a=μ)→(a=ν)→(μ=ν))",
            "@a.((a=μ)→(a=ν)→(μ=ν))→((λ=μ)→(λ=ν)→(μ=ν))",
            "(λ=μ)→(λ=ν)→(μ=ν)"
    };

    private static final String[] clearImplication = {
            "α→α→α",
            "α→(α→α)→α",
            "(α→α→α)→(α→(α→α)→α)→(α→α)",
            "(α→(α→α)→α)→(α→α)",
            "α→α",
            "α→(!α→α)",
            "(!α→(!α→!α))",
            "(!α→(!α→!α))→α→(!α→(!α→!α))",
            "α→(!α→(!α→!α))",
            "((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α)))",
            "((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α)))→α→((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α)))",
            "α→((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α)))",
            "(α→(!α→(!α→!α)))→(α→((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α))))→(α→((!α→((!α→!α)→!α))→(!α→!α)))",
            "(α→((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α))))→(α→((!α→((!α→!α)→!α))→(!α→!α)))",
            "α→((!α→((!α→!α)→!α))→(!α→!α))",
            "(!α→((!α→!α)→!α))",
            "(!α→((!α→!α)→!α))→α→(!α→((!α→!α)→!α))",
            "α→(!α→((!α→!α)→!α))",
            "(α→(!α→((!α→!α)→!α)))→(α→((!α→((!α→!α)→!α))→(!α→!α)))→(α→!α→!α)",
            "(α→((!α→((!α→!α)→!α))→(!α→!α)))→(α→!α→!α)",
            "α→(!α→!α)",
            "((!α→α)→((!α→!α)→!!α))",
            "((!α→α)→((!α→!α)→!!α))→α→((!α→α)→((!α→!α)→!!α))",
            "α→((!α→α)→((!α→!α)→!!α))",
            "(α→(!α→α))→(α→((!α→α)→((!α→!α)→!!α)))→(α→((!α→!α)→!!α))",
            "(α→((!α→α)→((!α→!α)→!!α)))→(α→((!α→!α)→!!α))",
            "α→((!α→!α)→!!α)",
            "(α→(!α→!α))→(α→((!α→!α)→!!α))→(α→!!α)",
            "(α→((!α→!α)→!!α))→(α→!!α)",
            "α→!!α",
            "(!!α→β)→α→(!!α→β)",
            "α→(!!α→β)",
            "(α→!!α)→(α→(!!α→β))→(α→β)",
            "(α→(!!α→β))→(α→β)",
            "α→β"
    };
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int x = reader.read();
        int y = reader.read();
        Proof proof = getProof(x, y);
        List<ProofLine> lines = proof.getLines();

        for (ProofLine line : lines) {
            Logical pep = formalArithmetic.parse(line.toString());
            System.out.println(pep.toString().replace("→", "->").replace("’", "'"));
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static Proof getProof(int x, int y) throws Exception {
        if (x % 2 == y % 2) {
            return new Proof(same(x, y));
        } else {
            return new Proof(different(x, y));
        }
    }

    private static List<ProofLine> same(int x, int y) throws Exception {
        String fullPattern = "((0’’*p=x)&(0’’*q=y))|((0’’*p=x+0’)&(0’’*q=y+0’))";
        List<ProofLine> result = Arrays.stream(arithmeticHelper).map(ProofLine::new).collect(Collectors.toList());
        String leftExpr, rightExpr;
        int p, q;
        if (x % 2 == 0) {
            result.addAll(getTwoXEqualsNumber(x));
            leftExpr = "(" + result.get(result.size() - 1).toString() + ")";
            result.addAll(getTwoXEqualsNumber(y));
            rightExpr = "(" + result.get(result.size() - 1).toString() + ")";
            p = x / 2;
            q = y / 2;
        } else {
            p = (x + 1) / 2;
            q = (y + 1) / 2;

            result.addAll(getTwoXEqualsNumber(x + 1));
            result.addAll(swapArguments(result.get(result.size() - 1).toString()));
            result.addAll(aIncEqualsAPlusOne(getNumber(x)));
            result.addAll(secondImpl(getNumber(x + 1), "0’’*" + getNumber(p), getNumber(x) + "+0’"));
            result.add(new ProofLine("(" + getNumber(x + 1) + "=" + getNumber(x) + "+0’)→(0’’*" + getNumber(p) + "=" + getNumber(x) + "+0’)"));
            result.add(new ProofLine("0’’*" + getNumber(p) + "=" + getNumber(x) + "+0’"));
            leftExpr = "(0’’*" + getNumber(p) + "=" + getNumber(x) + "+0’)";

            result.addAll(getTwoXEqualsNumber(y + 1));
            result.addAll(swapArguments(result.get(result.size() - 1).toString()));
            result.addAll(aIncEqualsAPlusOne(getNumber(y)));
            result.addAll(secondImpl(getNumber(y + 1), "0’’*" + getNumber(q), getNumber(y) + "+0’"));
            result.add(new ProofLine("(" + getNumber(y + 1) + "=" + getNumber(y) + "+0’)→(0’’*" + getNumber(q) + "=" + getNumber(y) + "+0’)"));
            result.add(new ProofLine("0’’*" + getNumber(q) + "=" + getNumber(y) + "+0’"));
            rightExpr = "(0’’*" + getNumber(q) + "=" + getNumber(y) + "+0’)";
        }
        String fullWithoutPQ = fullPattern.replace("x", getNumber(x)).replace("y", getNumber(y));
        String fullWithoutQ = fullWithoutPQ.replace("p", getNumber(p));
        String full = fullWithoutQ.replace("q", getNumber(q));

        result.add(new ProofLine(leftExpr + "→" + rightExpr + "→" + leftExpr + "&" + rightExpr));
        result.add(new ProofLine(rightExpr + "→" + leftExpr + "&" + rightExpr));
        result.add(new ProofLine(leftExpr + "&" + rightExpr));
        result.add(new ProofLine(leftExpr + "&" + rightExpr + "→" + full));
        result.add(new ProofLine(full));

        result.add(new ProofLine(full + "→?q.(" + fullWithoutQ + ")"));
        result.add(new ProofLine("?q.(" + fullWithoutQ + ")"));
        result.add(new ProofLine("?q.(" + fullWithoutQ + ")→?p.?q.(" + fullWithoutPQ + ")"));
        result.add(new ProofLine("?p.?q.(" + fullWithoutPQ + ")"));
        return result;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private static List<ProofLine> different(int x, int y) throws Exception {
        List<ProofLine> left, right;
        String leftLetter, rightLetter;
        int leftNumber, rightNumber;
        List<ProofLine> result = Arrays.stream(arithmeticHelper).map(ProofLine::new).collect(Collectors.toList());
        if (x % 2 == 1) {
            leftNumber = x;
            rightNumber = y + 1;
            leftLetter = "p";
            rightLetter = "q";
        } else {
            leftNumber = y;
            rightNumber = x + 1;
            leftLetter = "q";
            rightLetter = "p";
        }

        left = getNotTwoXEqualsNumber(leftNumber);
        right = getNotTwoXEqualsNumber(rightNumber);
        left = left.stream().map(line -> new ProofLine(line.toString().replace("x", leftLetter))).collect(Collectors.toList());
        right = right.stream().map(line -> new ProofLine(line.toString().replace("x", rightLetter))).collect(Collectors.toList());

        String firstLeftPart = "((0’’*p=" + getNumber(x) + ")&(0’’*q=" + getNumber(y) + "))";
        String secondLeftPart = "(0’’*" + leftLetter + "=" + getNumber(leftNumber) + ")";
        left.add(new ProofLine(firstLeftPart + "→" + secondLeftPart));
        left.addAll(getContraposition(firstLeftPart, secondLeftPart));

        String firstRightPart = "((0’’*p=" + getNumber(x + 1) + ")&(0’’*q=" + getNumber(y + 1) + "))";
        String secondRightPart = "(0’’*" + rightLetter + "=" + getNumber(rightNumber) + ")";
        right.add(new ProofLine(firstRightPart + "→" + secondRightPart));
        right.addAll(getContraposition(firstRightPart, secondRightPart));

        result.addAll(left);
        result.addAll(right);
        result.addAll(Arrays.stream(LemmaUtil.disjunctionFFLemma.replace(LogicUtil.createReplacing(firstLeftPart, firstRightPart)))
                .map(ProofLine::new).collect(Collectors.toList()));

        String formula = "(" + result.get(result.size() - 1).toString().substring(1) + ")";
        result.addAll(generalize("!" + formula, "p"));
        result.addAll(morganize(formula, "p"));
        result.add(new ProofLine("!?p." + formula));
        result.addAll(generalize("!?p." + formula, "q"));
        result.addAll(morganize("?p." + formula, "q"));
        result.add(new ProofLine("!?q.?p." + formula));
        return result;
    }

    private static List<ProofLine> getTwoXEqualsNumber(int number) throws Exception {
        List<ProofLine> lines = new ArrayList<>();
        if (number % 2 != 0 || number < 0) {
            throw new Exception("kek");
        }

        int k = 0;
        while (k != number + 2) {
            String full = getNumber(k);
            String half = getNumber(k / 2);
            if (k != 0) {
                String previous = getNumber((k - 2) / 2);
                lines.add(new ProofLine("g+0=g"));
                lines.add(new ProofLine("@a.(a+0=a)→(0’’*" + previous + "+0=0’’*" + previous + ")"));
                lines.add(new ProofLine("0’’*" + previous + "+0=0’’*" + previous));
                lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=0’’*" + previous + ")→(a’=(0’’*" + previous + ")’))"));
                lines.add(new ProofLine("@a.((a=0’’*" + previous + ")→(a’=(0’’*" + previous + ")’))"));
                lines.add(new ProofLine("@a.((a=0’’*" + previous + ")→(a’=(0’’*" + previous + ")’))→(" + "(0’’*" + previous + "+0=0’’*" + previous + ")→((0’’*" + previous + "+0)’=(0’’*" + previous + ")’))"));
                lines.add(new ProofLine("(0’’*" + previous + "+0=0’’*" + previous + ")→((0’’*" + previous + "+0)’=(0’’*" + previous + ")’)"));
                lines.add(new ProofLine("(0’’*" + previous + "+0)’=(0’’*" + previous + ")’"));
                lines.add(new ProofLine("@b.@a.((a+b’)=(a+b)’)→@a.(a+0’=(a+0)’)"));
                lines.add(new ProofLine("@a.(a+0’=(a+0)’)"));
                lines.add(new ProofLine("@a.(a+0’=(a+0)’)→(0’’*" + previous + "+0’=(0’’*" + previous + "+0)’)"));
                lines.add(new ProofLine("0’’*" + previous + "+0’=(0’’*" + previous + "+0)’"));
                lines.addAll(swap("(0’’*" + previous + "+0’)", "((0’’*" + previous + "+0)’)"));
                lines.addAll(secondImpl("(0’’*" + previous + "+0)’", "0’’*" + previous + "+0’", "(0’’*" + previous + ")’"));
                lines.add(new ProofLine("g+0=g"));
                lines.add(new ProofLine("((0’’*" + previous + "+0)’=(0’’*" + previous + ")’)→(0’’*" + previous + "+0’=(0’’*" + previous + ")’)"));
                lines.add(new ProofLine("0’’*" + previous + "+0’=(0’’*" + previous + ")’"));
                lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=(0’’*" + previous + ")’)→(a’=(0’’*" + previous + ")’’))"));
                lines.add(new ProofLine("@a.((a=(0’’*" + previous + ")’)→(a’=(0’’*" + previous + ")’’))"));
                lines.add(new ProofLine("@a.((a=(0’’*" + previous + ")’)→(a’=(0’’*" + previous + ")’’))→((0’’*" + previous + "+0’=(0’’*" + previous + ")’)→((0’’*" + previous + "+0’)’=(0’’*" + previous + ")’’))"));
                lines.add(new ProofLine("(0’’*" + previous + "+0’=(0’’*" + previous + ")’)→((0’’*" + previous + "+0’)’=(0’’*" + previous + ")’’)"));
                lines.add(new ProofLine("(0’’*" + previous + "+0’)’=(0’’*" + previous + ")’’"));
                lines.add(new ProofLine("@b.@a.((a+b’)=(a+b)’)→@a.(a+0’’=(a+0’)’)"));
                lines.add(new ProofLine("@a.(a+0’’=(a+0’)’)"));
                lines.add(new ProofLine("@a.(a+0’’=(a+0’)’)→(0’’*" + previous + "+0’’=(0’’*" + previous + "+0’)’)"));
                lines.add(new ProofLine("0’’*" + previous + "+0’’=(0’’*" + previous + "+0’)’"));
                lines.addAll(swap("(0’’*" + previous + "+0’’)", "((0’’*" + previous + "+0’)’)"));
                lines.addAll(secondImpl("(0’’*" + previous + "+0’)’", "0’’*" + previous + "+0’’", "(0’’*" + previous + ")’’"));
                lines.add(new ProofLine("g+0=g"));
                lines.add(new ProofLine("((0’’*" + previous + "+0’)’=(0’’*" + previous + ")’’)→(0’’*" + previous + "+0’’=(0’’*" + previous + ")’’)"));
                lines.add(new ProofLine("0’’*" + previous + "+0’’=(0’’*" + previous + ")’’"));
                lines.add(new ProofLine("@b.@a.((a*b’)=((a*b)+a))→@a.(a*" + half + "=a*" + previous + "+a)"));
                lines.add(new ProofLine("@a.(a*" + half + "=a*" + previous + "+a)"));
                lines.add(new ProofLine("@a.(a*" + half + "=a*" + previous + "+a)→(0’’*" + half + "=0’’*" + previous + "+0’’)"));
                lines.add(new ProofLine("0’’*" + half + "=0’’*" + previous + "+0’’"));
                lines.addAll(swap("(0’’*" + half + ")", "(0’’*" + previous + "+0’’)"));
                lines.addAll(secondImpl("0’’*" + previous + "+0’’", "(0’’*" + previous + ")’’", "0’’*" + half));
                lines.add(new ProofLine("g+0=g"));
                lines.add(new ProofLine("(0’’*" + previous + "+0’’=0’’*" + half + ")→((0’’*" + previous + ")’’=0’’*" + half + ")"));
                lines.add(new ProofLine("(0’’*" + previous + ")’’=0’’*" + half + ""));
                lines.addAll(secondImpl("(0’’*" + previous + ")’’", "0’’*" + half, full));
                lines.add(new ProofLine("g+0=g"));
                lines.add(new ProofLine("((0’’*" + previous + ")’’=" + full + ")→(0’’*" + half + "=" + full + ")"));
            } else {
                lines.add(new ProofLine("@a.(a*0=0)→(0’’*0=0)"));
            }
            lines.add(new ProofLine("0’’*" + half + "=" + full));
            if (k != number) {
                lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=" + full + ")→(a’=" + full + "’))"));
                lines.add(new ProofLine("@a.((a=" + full + ")→(a’=" + full + "’))"));
                lines.add(new ProofLine("@a.((a=" + full + ")→(a’=" + full + "’))→((0’’*" + half + "=" + full + ")→((0’’*" + half + ")’=" + full + "’))"));
                lines.add(new ProofLine("(0’’*" + half + "=" + full + ")→((0’’*" + half + ")’=" + full + "’)"));
                lines.add(new ProofLine("(0’’*" + half + ")’=" + full + "’"));
                lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=" + full + "’)→(a’=" + full + "’’))"));
                lines.add(new ProofLine("@a.((a=" + full + "’)→(a’=" + full + "’’))"));
                lines.add(new ProofLine("@a.((a=" + full + "’)→(a’=" + full + "’’))→(((0’’*" + half + ")’=" + full + "’)→((0’’*" + half + ")’’=" + full + "’’))"));
                lines.add(new ProofLine("((0’’*" + half + ")’=" + full + "’)→((0’’*" + half + ")’’=" + full + "’’)"));
                lines.add(new ProofLine("(0’’*" + half + ")’’=" + full + "’’"));
            }
            k += 2;
        }
        return lines;
    }

    private static List<ProofLine> getNotTwoXEqualsNumber(int number) throws Exception {
        List<ProofLine> lines = new ArrayList<>();
        if (number % 2 == 0 || number < 0) {
            throw new Exception("kek");
        }

        int k = 1;
        while (k != number + 2) {
            String kek = getNumber(k);
            if (k != 1) {
                String previous = getNumber(k - 2);
                lines.add(new ProofLine("@b.@a.((a’=b’)→(a=b))→@a.((a’=" + previous + "’)→(a=" + previous + "))"));
                lines.add(new ProofLine("@a.((a’=" + previous + "’)→(a=" + previous + "))"));
                lines.add(new ProofLine("@a.((a’=" + previous + "’)→(a=" + previous + "))→(((0’’*x)’=" + previous + "’)→((0’’*x)=" + previous + "))"));
                lines.add(new ProofLine("((0’’*x)’=" + previous + "’)→((0’’*x)=" + previous + ")"));
                lines.addAll(getContraposition("((0’’*x)’=" + previous + "’)", "((0’’*x)=" + previous + ")"));
                lines.add(new ProofLine("!((0’’*x)’=" + previous + "’)"));
            } else {
                lines.add(new ProofLine("@a.!(a’=0)→!((0’’*x)’=0)"));
                lines.add(new ProofLine("!((0’’*x)’=0)"));
            }
            lines.add(new ProofLine("@b.@a.((a’=b’)→(a=b))→@a.((a’=" + kek + ")→(a=" + kek.substring(0, kek.length() - 1) + "))"));
            lines.add(new ProofLine("@a.((a’=" + kek + ")→(a=" + kek.substring(0, kek.length() - 1) + "))"));
            lines.add(new ProofLine("@a.((a’=" + kek + ")→(a=" + kek.substring(0, kek.length() - 1) + "))→(((0’’*x)’’=" + kek + ")→((0’’*x)’=" + kek.substring(0, kek.length() - 1) + "))"));
            lines.add(new ProofLine("((0’’*x)’’=" + kek + ")→((0’’*x)’=" + kek.substring(0, kek.length() - 1) + ")"));
            lines.addAll(getContraposition("((0’’*x)’’=" + kek + ")", "((0’’*x)’=" + kek.substring(0, kek.length() - 1) + ")"));
            
            lines.add(new ProofLine("!((0’’*x)’’=" + kek + ")"));
            lines.add(new ProofLine("@b.@a.((a+b’)=(a+b)’)→@a.(a+0’’=(a+0’)’)"));
            lines.add(new ProofLine("@a.(a+0’’=(a+0’)’)"));
            lines.add(new ProofLine("@a.(a+0’’=(a+0’)’)→(0’’*x+0’’=(0’’*x+0’)’)"));
            lines.add(new ProofLine("0’’*x+0’’=(0’’*x+0’)’"));
            lines.add(new ProofLine("@a.(a+0=a)→(0’’*x+0’’+0=0’’*x+0’’)"));
            lines.add(new ProofLine("0’’*x+0’’+0=0’’*x+0’’"));
            lines.addAll(eq("0’’*x+0’’"));
            lines.addAll(secondImpl("0’’*x+0’’", "(0’’*x+0’)’", "0’’*x+0’’"));
            lines.add(new ProofLine("(0’’*x+0’’=0’’*x+0’’)→((0’’*x+0’)’=0’’*x+0’’)"));
            lines.add(new ProofLine("(0’’*x+0’)’=0’’*x+0’’"));

            lines.add(new ProofLine("@b.@a.((a+b’)=(a+b)’)→@a.(a+0’=(a+0)’)"));
            lines.add(new ProofLine("@a.(a+0’=(a+0)’)"));
            lines.add(new ProofLine("@a.(a+0’=(a+0)’)→(0’’*x+0’=(0’’*x+0)’)"));
            lines.add(new ProofLine("0’’*x+0’=(0’’*x+0)’"));
            lines.add(new ProofLine("@a.(a+0=a)→(0’’*x+0=0’’*x)"));
            lines.add(new ProofLine("0’’*x+0=0’’*x"));
            lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=0’’*x)→(a’=(0’’*x)’))"));
            lines.add(new ProofLine("@a.((a=0’’*x)→(a’=(0’’*x)’))"));
            lines.add(new ProofLine("@a.((a=0’’*x)→(a’=(0’’*x)’))→((0’’*x+0=0’’*x)→((0’’*x+0)’=(0’’*x)’))"));
            lines.add(new ProofLine("(0’’*x+0=0’’*x)→((0’’*x+0)’=(0’’*x)’)"));
            lines.add(new ProofLine("(0’’*x+0)’=(0’’*x)’"));
            lines.add(new ProofLine("@a.(a+0=a)→(0’’*x+0’+0=0’’*x+0’)"));
            lines.add(new ProofLine("0’’*x+0’+0=0’’*x+0’"));
            lines.addAll(eq("0’’*x+0’"));
            lines.addAll(secondImpl("0’’*x+0’", "(0’’*x+0)’", "0’’*x+0’"));
            lines.add(new ProofLine("(0’’*x+0’=0’’*x+0’)→((0’’*x+0)’=0’’*x+0’)"));
            lines.add(new ProofLine("(0’’*x+0)’=0’’*x+0’"));
            lines.addAll(secondImpl("(0’’*x+0)’", "0’’*x+0’", "(0’’*x)’"));
            lines.add(new ProofLine("((0’’*x+0)’=(0’’*x)’)→(0’’*x+0’=(0’’*x)’)"));
            lines.add(new ProofLine("0’’*x+0’=(0’’*x)’"));
            lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=(0’’*x)’)→(a’=(0’’*x)’’))"));
            lines.add(new ProofLine("@a.((a=(0’’*x)’)→(a’=(0’’*x)’’))"));
            lines.add(new ProofLine("@a.((a=(0’’*x)’)→(a’=(0’’*x)’’))→((0’’*x+0’=(0’’*x)’)→((0’’*x+0’)’=(0’’*x)’’))"));
            lines.add(new ProofLine("(0’’*x+0’=(0’’*x)’)→((0’’*x+0’)’=(0’’*x)’’)"));
            lines.add(new ProofLine("(0’’*x+0’)’=(0’’*x)’’"));

            lines.addAll(secondImpl("(0’’*x+0’)’", "0’’*x+0’’", "(0’’*x)’’"));
            lines.add(new ProofLine("((0’’*x+0’)’=(0’’*x)’’)→(0’’*x+0’’=(0’’*x)’’)"));
            lines.add(new ProofLine("0’’*x+0’’=(0’’*x)’’"));

            lines.addAll(secondImpl("0’’*x+0’’", "(0’’*x)’’", kek));
            lines.add(new ProofLine("(0’’*x+0’’=" + kek + ")→((0’’*x)’’=" + kek + ")"));
            lines.addAll(getContraposition("(0’’*x+0’’=" + kek + ")", "((0’’*x)’’=" + kek + ")"));
            lines.add(new ProofLine("!(0’’*x+0’’=" + kek + ")"));
            
            lines.add(new ProofLine("!(0’’*x+0’’=" + kek + ")→!((0’’*x)=" + kek + ")→!(0’’*x+0’’=" + kek + ")"));
            lines.add(new ProofLine("!((0’’*x)=" + kek + ")→!(0’’*x+0’’=" + kek + ")"));

            lines.add(new ProofLine("@b.@a.((a*b’)=((a*b)+a))→@a.(a*x’=a*x+a)"));
            lines.add(new ProofLine("@a.(a*x’=a*x+a)"));
            lines.add(new ProofLine("@a.(a*x’=a*x+a)→(0’’*x’=0’’*x+0’’)"));
            lines.add(new ProofLine("0’’*x’=0’’*x+0’’"));
            lines.addAll(secondImpl("0’’*x’", "0’’*x+0’’", kek));
            lines.add(new ProofLine("(0’’*x’=" + kek + ")→(0’’*x+0’’=" + kek + ")"));
            lines.addAll(getContraposition("(0’’*x’=" + kek + ")", "(0’’*x+0’’=" + kek + ")"));
            lines.add(new ProofLine("(!(0’’*x+0’’=" + kek + ")→!(0’’*x’=" + kek + "))→(!(0’’*x=" + kek + "))→(!(0’’*x+0’’=" + kek + ")→!(0’’*x’=" + kek + "))"));
            lines.add(new ProofLine("(!(0’’*x=" + kek + "))→(!(0’’*x+0’’=" + kek + ")→!(0’’*x’=" + kek + "))")); // m.p.
            lines.add(new ProofLine("((!(0’’*x=" + kek + "))→!(0’’*x+0’’=" + kek + "))→((!(0’’*x=" + kek + "))→(!(0’’*x+0’’=" + kek + ")→!(0’’*x’=" + kek + ")))→((!(0’’*x=" + kek + "))→!(0’’*x’=" + kek + "))"));
            lines.add(new ProofLine("((!(0’’*x=" + kek + "))→(!(0’’*x+0’’=" + kek + ")→!(0’’*x’=" + kek + ")))→((!(0’’*x=" + kek + "))→!(0’’*x’=" + kek + "))"));
            lines.add(new ProofLine("((!(0’’*x=" + kek + "))→!(0’’*x’=" + kek + "))"));
            lines.addAll(generalize("(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + "))", "x"));

            lines.addAll(getNotTwoTimesZeroEqualsNumber(k));
            lines.add(new ProofLine("!(0’’*0=" + kek + ")→@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + "))→((!(0’’*0=" + kek + "))&(@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + "))))"));
            lines.add(new ProofLine("@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + "))→((!(0’’*0=" + kek + "))&(@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + "))))"));
            lines.add(new ProofLine("(!(0’’*0=" + kek + "))&(@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + ")))"));
            lines.add(new ProofLine("(!(0’’*0=" + kek + "))&(@x.(!((0’’*x)=" + kek + ")→!(0’’*x’=" + kek + ")))→!((0’’*x)=" + kek + ")"));
            lines.add(new ProofLine("!((0’’*x)=" + kek + ")"));
            k += 2;
        }

        return lines;
    }

    private static List<ProofLine> getNotTwoTimesZeroEqualsNumber(int n) throws Exception {
        List<ProofLine> lines = new ArrayList<>();
        if (n == 0) {
            throw new Exception("kek");
        }

        String number = getNumber(n);
        lines.add(new ProofLine("@a.(a*0=0)→(0’’*0=0)"));
        lines.add(new ProofLine("0’’*0=0"));
        lines.addAll(secondImpl("0’’*0", "0", number));

        lines.add(new ProofLine("(0’’*0=" + number + ")→(0=" + number + ")"));
        lines.addAll(getContraposition("(0’’*0=" + number + ")", "(0=" + number + ")"));
        lines.addAll(secondImpl("0", number, "0"));

        lines.add(new ProofLine("@a.(a+0=a)→(0+0=0)"));
        lines.add(new ProofLine("0+0=0"));
        lines.addAll(secondImpl("0+0", "0", "0"));
        lines.add(new ProofLine("(0+0=0)→(0=0)"));
        lines.add(new ProofLine("0=0"));
        lines.add(new ProofLine("(0=0)→(0=" + number + ")→(0=0)"));
        lines.add(new ProofLine("(0=" + number + ")→(0=0)"));

        lines.add(new ProofLine("((0=" + number + ")→(0=0))→((0=" + number + ")→(0=0)→(" + number + "=0))→((0=" + number + ")→(" + number + "=0))"));
        lines.add(new ProofLine("((0=" + number + ")→(0=0)→(" + number + "=0))→((0=" + number + ")→(" + number + "=0))"));
        lines.add(new ProofLine("(0=" + number + ")→(" + number + "=0)"));
        lines.addAll(getContraposition("(0=" + number + ")", "(" + number + "=0)"));

        lines.add(new ProofLine("@a.!(a’=0)→!(" + number + "=0)"));
        lines.add(new ProofLine("!(" + number + "=0)"));
        lines.add(new ProofLine("!(0=" + number + ")"));
        lines.add(new ProofLine("!(0’’*0=" + number + ")"));
        return lines;
    }

    private static List<ProofLine> generalize(String expr, String variable) {
        List<ProofLine> lines = new ArrayList<>();
        lines.add(new ProofLine(expr + "→!(y’=0)→" + expr));
        lines.add(new ProofLine("!(y’=0)→" + expr));
        lines.add(new ProofLine("!(y’=0)→@" + variable + "." + expr));
        lines.add(new ProofLine("!(y’=0)"));
        lines.add(new ProofLine("@" + variable + "." + expr));
        return lines;
    }

    private static List<ProofLine> morganize(String expr, String variable) {
        List<ProofLine> lines = new ArrayList<>();
        lines.add(new ProofLine("@" + variable + ".!" + expr + "→!" + expr));
        lines.addAll(getContraposition("@" + variable + ".!" + expr, "!" + expr));
        lines.addAll(removeNotNotFromFirstPartOfImplication(expr, "!@" + variable + ".!" + expr));
        lines.add(new ProofLine("?" + variable + "." + expr + "→!@" + variable + ".!" + expr));
        lines.addAll(getContraposition("?" + variable + "." + expr, "!@" + variable + ".!" + expr));
        lines.addAll(removeNotNotFromFirstPartOfImplication("@" + variable + ".!" + expr, "!?" + variable + "." + expr));
        return lines;
    }

    private static List<ProofLine> aIncEqualsAPlusOne(String expr) {
        List<ProofLine> lines = new ArrayList<>();
        lines.add(new ProofLine("@b.@a.((a+b’)=(a+b)’)→@a.(a+0’=(a+0)’)"));
        lines.add(new ProofLine("@a.(a+0’=(a+0)’)"));
        lines.add(new ProofLine("@a.(a+0’=(a+0)’)→(" + expr + "+0’=(" + expr + "+0)’)"));
        lines.add(new ProofLine(expr + "+0’=(" + expr + "+0)’"));
        lines.addAll(swap("(" + expr + "+0’)", "(" + expr + "+0)’"));
        lines.add(new ProofLine("@a.(a+0=a)→(" + expr + "+0=" + expr + ")"));
        lines.add(new ProofLine(expr + "+0=" + expr));
        lines.add(new ProofLine("@b.@a.((a=b)→(a’=b’))→@a.((a=" + expr + ")→(a’=" + expr + "’))"));
        lines.add(new ProofLine("@a.((a=" + expr + ")→(a’=" + expr + "’))"));
        lines.add(new ProofLine("@a.((a=" + expr + ")→(a’=" + expr + "’))→((" + expr + "+0=" + expr + ")→((" + expr + "+0)’=" + expr + "’))"));
        lines.add(new ProofLine("(" + expr + "+0=" + expr + ")→((" + expr + "+0)’=" + expr + "’)"));
        lines.add(new ProofLine("(" + expr + "+0)’=" + expr + "’"));
        lines.addAll(secondImpl("(" + expr + "+0)’", expr + "’", expr + "+0’"));
        lines.add(new ProofLine("((" + expr + "+0)’=" + expr + "+0’)→(" + expr + "’=" + expr + "+0’)"));
        lines.add(new ProofLine(expr + "’=" + expr + "+0’"));
        return lines;
    }
    
    private static List<ProofLine> getContraposition(String firstExpr, String secondExpr) {
        List<ProofLine> lines = new ArrayList<>();
        for (String expr : contraposition) {
            lines.add(new ProofLine(expr.replace("α", firstExpr).replace("β", secondExpr)));
        }
        return lines;
    }

    private static List<ProofLine> removeNotNotFromFirstPartOfImplication(String firstExpr, String secondExpr) {
        List<ProofLine> lines = new ArrayList<>();
        for (String expr : clearImplication) {
            lines.add(new ProofLine(expr.replace("α", firstExpr).replace("β", secondExpr)));
        }
        return lines;
    }

    private static List<ProofLine> swapArguments(String expr) {
        String[] arguments = expr.split("=");
        return swap("(" + arguments[0] + ")", "(" + arguments[1] + ")");
    }

    private static List<ProofLine> swap(String firstTerm, String secondTerm) {
        List<ProofLine> lines = new ArrayList<>();
        for (String expr : swap) {
            lines.add(new ProofLine(expr.replace("λ", firstTerm).replace("μ", secondTerm)));
        }
        return lines;
    }

    private static List<ProofLine> eq(String firstTerm) {
        List<ProofLine> lines = new ArrayList<>();
        for (String expr : eq) {
            lines.add(new ProofLine(expr.replace("λ", firstTerm)));
        }
        return lines;
    }

    private static List<ProofLine> secondImpl(String firstTerm, String secondTerm, String thirdTerm) {
        List<ProofLine> lines = new ArrayList<>();
        for (String expr : secondArithmeticAxiomImpl) {
            lines.add(new ProofLine(expr.replace("λ", firstTerm).replace("μ", secondTerm).replace("ν", thirdTerm)));
        }
        return lines;
    }

    private static String getNumber(int n) {
        return "0" + StringUtil.repeat("’", n);
    }
}
