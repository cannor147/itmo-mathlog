package my.util;

import expressions.Logical;
import my.Lemma;

@SuppressWarnings("WeakerAccess")
public class LemmaUtil {
    public static final Lemma aImplALemma = new Lemma(new String[] {
            "(α→(α→α))",
            "((α→(α→α))→((α→((α→α)→α))→(α→α)))",
            "((α→((α→α)→α))→(α→α))",
            "(α→((α→α)→α))",
            "(α→α)"
    });

    public static final Lemma aOrNotALemma = new Lemma(new String[]{
            "(α)→((α)|!(α))",
            "(!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))",
            "((!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α))))→(((α)→((α)|!(α)))→((!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))))",
            "((α)→((α)|!(α)))→((!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α))))",
            "!((α)|!(α))→((α)→!((α)|!(α)))",
            "(!((α)|!(α))→((α)→!((α)|!(α))))→(((α)→((α)|!(α)))→(!((α)|!(α))→((α)→!((α)|!(α)))))",
            "((α)→((α)|!(α)))→(!((α)|!(α))→((α)→!((α)|!(α))))",
            "(((α)→((α)|!(α)))→(!((α)|!(α))→((α)→!((α)|!(α)))))→((((α)→((α)|!(α)))→((!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))))→(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))))",
            "(((α)→((α)|!(α)))→((!((α)|!(α))→((α)→!((α)|!(α))))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))))→(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α))))",
            "((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α)))",
            "(!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))",
            "((!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))→(((α)→((α)|!(α)))→((!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))))",
            "((α)→((α)|!(α)))→((!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))",
            "((α)→((α)|!(α)))→(!((α)|!(α))→((α)→((α)|!(α))))",
            "(((α)→((α)|!(α)))→(!((α)|!(α))→((α)→((α)|!(α)))))→((((α)→((α)|!(α)))→((!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))))→(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))))",
            "(((α)→((α)|!(α)))→((!((α)|!(α))→((α)→((α)|!(α))))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))))→(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))",
            "((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))",
            "(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))",
            "((((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))))→(((α)→((α)|!(α)))→((((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))))",
            "((α)→((α)|!(α)))→((((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))))",
            "((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))",
            "(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(((α)→((α)|!(α)))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))",
            "((α)→((α)|!(α)))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))",
            "(((α)→((α)|!(α)))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→((((α)→((α)|!(α)))→((((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))))→(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))))",
            "(((α)→((α)|!(α)))→((((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))))→(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))))",
            "((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))",
            "(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α)))))→((((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))→(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))",
            "(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→((α)|!(α)))→(((α)→!((α)|!(α)))→!(α))))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))))→(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))",
            "((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))",
            "(((α)→((α)|!(α)))→(!((α)|!(α))→(((α)→!((α)|!(α)))→!(α))))→((((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α))))→(((α)→((α)|!(α)))→(!((α)|!(α))→!(α))))",
            "(((α)→((α)|!(α)))→((!((α)|!(α))→(((α)→!((α)|!(α)))→!(α)))→(!((α)|!(α))→!(α))))→(((α)→((α)|!(α)))→(!((α)|!(α))→!(α)))",
            "((α)→((α)|!(α)))→(!((α)|!(α))→!(α))",
            "!((α)|!(α))→!(α)",
            "!(α)→((α)|!(α))",
            "(!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))",
            "((!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))))",
            "(!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α))))",
            "!((α)|!(α))→(!(α)→!((α)|!(α)))",
            "(!((α)|!(α))→(!(α)→!((α)|!(α))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→(!(α)→!((α)|!(α)))))",
            "(!(α)→((α)|!(α)))→(!((α)|!(α))→(!(α)→!((α)|!(α))))",
            "((!(α)→((α)|!(α)))→(!((α)|!(α))→(!(α)→!((α)|!(α)))))→(((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))))",
            "((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→!((α)|!(α))))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α))))",
            "(!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α)))",
            "(!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))",
            "((!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))))",
            "(!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))",
            "(!(α)→((α)|!(α)))→(!((α)|!(α))→(!(α)→((α)|!(α))))",
            "((!(α)→((α)|!(α)))→(!((α)|!(α))→(!(α)→((α)|!(α)))))→(((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))))",
            "((!(α)→((α)|!(α)))→((!((α)|!(α))→(!(α)→((α)|!(α))))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))))→((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))",
            "(!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))",
            "((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))",
            "(((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))))→((!(α)→((α)|!(α)))→(((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))))",
            "(!(α)→((α)|!(α)))→(((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))))",
            "(!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))",
            "((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→((!(α)→((α)|!(α)))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))",
            "(!(α)→((α)|!(α)))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))",
            "((!(α)→((α)|!(α)))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(((!(α)→((α)|!(α)))→(((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))))",
            "((!(α)→((α)|!(α)))→(((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))))",
            "(!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))",
            "((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α)))))→(((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))",
            "((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→((α)|!(α)))→((!(α)→!((α)|!(α)))→!!(α))))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))",
            "(!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))",
            "((!(α)→((α)|!(α)))→(!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α))))→(((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→!!(α))))",
            "((!(α)→((α)|!(α)))→((!((α)|!(α))→((!(α)→!((α)|!(α)))→!!(α)))→(!((α)|!(α))→!!(α))))→((!(α)→((α)|!(α)))→(!((α)|!(α))→!!(α)))",
            "(!(α)→((α)|!(α)))→(!((α)|!(α))→!!(α))",
            "(!((α)|!(α))→!!(α))",
            "(!((α)|!(α))→!(α))→((!((α)|!(α))→!(!(α)))→!(!((α)|!(α))))",
            "(!((α)|!(α))→!(!(α)))→!(!((α)|!(α)))",
            "!(!((α)|!(α)))",
            "!(!((α)|!(α)))→((α)|!(α))",
            "(α)|!(α)"
    });

    public static final Lemma conjunctionFFLemma = new Lemma(new String[]{
            "((α)&(β))→(α)",
            "!(α)",
            "!(α)→(((α)&(β))→!(α))",
            "((α)&(β))→!(α)",
            "(((α)&(β))→(α))→((((α)&(β))→!(α))→!((α)&(β)))",
            "(((α)&(β))→!(α))→!((α)&(β))",
            "!((α)&(β))"
    });

    public static final Lemma conjunctionFTLemma = new Lemma(new String[]{
            "((α)&(β))→(α)",
            "!(α)→(((α)&(β))→!(α))",
            "!(α)",
            "((α)&(β))→!(α)",
            "(((α)&(β))→(α))→((((α)&(β))→!(α))→!((α)&(β)))",
            "(((α)&(β))→!(α))→!((α)&(β))",
            "!((α)&(β))"
    });

    public static final Lemma conjunctionTFLemma = new Lemma(new String[]{
            "((α)&(β))→(β)",
            "!(β)",
            "!(β)→(((α)&(β))→!(β))",
            "((α)&(β))→!(β)",
            "(((α)&(β))→(β))→((((α)&(β))→!(β))→!((α)&(β)))",
            "(((α)&(β))→!(β))→!((α)&(β))",
            "!((α)&(β))"
    });

    public static final Lemma conjunctionTTLemma = new Lemma(new String[]{
            "(α)",
            "(β)",
            "(α)→((β)→((α)&(β)))",
            "(β)→((α)&(β))",
            "(α)&(β)"
    });

    public static final Lemma disjunctionFFLemma = new Lemma(new String[]{
            "!(α)",
            "!(β)",
            "(((α)|(β))→(α))→((((α)|(β))→!(α))→!((α)|(β)))",
            "!(α)→(((α)|(β))→!(α))",
            "((α)|(β))→!(α)",
            "!(α)",
            "(!(α)→(((α)|(β))→!(α)))",
            "(((α)|(β))→!(α))",
            "!(β)",
            "(!(β)→(((α)|(β))→!(β)))",
            "(((α)|(β))→!(β))",
            "(((α)|(β))→(((α)|(β))→((α)|(β))))",
            "(((α)|(β))→((((α)|(β))→((α)|(β)))→((α)|(β))))",
            "((((α)|(β))→(((α)|(β))→((α)|(β))))→((((α)|(β))→((((α)|(β))→((α)|(β)))→((α)|(β))))→(((α)|(β))→((α)|(β)))))",
            "((((α)|(β))→((((α)|(β))→((α)|(β)))→((α)|(β))))→(((α)|(β))→((α)|(β))))",
            "(((α)|(β))→((α)|(β)))",
            "((α)→((α)→(α)))",
            "(((α)→((α)→(α)))→(((α)|(β))→((α)→((α)→(α)))))",
            "(((α)|(β))→((α)→((α)→(α))))",
            "(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α))))",
            "((((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α))))→(((α)|(β))→(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α))))))",
            "(((α)|(β))→(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α)))))",
            "((α)→(((α)→(α))→(α)))",
            "(((α)→(((α)→(α))→(α)))→(((α)|(β))→((α)→(((α)→(α))→(α)))))",
            "(((α)|(β))→((α)→(((α)→(α))→(α))))",
            "((((α)|(β))→((α)→((α)→(α))))→((((α)|(β))→(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α)))))→(((α)|(β))→(((α)→(((α)→(α))→(α)))→((α)→(α))))))",
            "((((α)|(β))→(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α)))))→(((α)|(β))→(((α)→(((α)→(α))→(α)))→((α)→(α)))))",
            "(((α)|(β))→(((α)→(((α)→(α))→(α)))→((α)→(α))))",
            "((((α)|(β))→((α)→(((α)→(α))→(α))))→((((α)|(β))→(((α)→(((α)→(α))→(α)))→((α)→(α))))→(((α)|(β))→((α)→(α)))))",
            "((((α)|(β))→(((α)→(((α)→(α))→(α)))→((α)→(α))))→(((α)|(β))→((α)→(α))))",
            "(((α)|(β))→((α)→(α)))",
            "!(β)",
            "(!(β)→(((α)|(β))→!(β)))",
            "(((α)|(β))→!(β))",
            "(!(β)→((β)→!(β)))",
            "((!(β)→((β)→!(β)))→(((α)|(β))→(!(β)→((β)→!(β)))))",
            "(((α)|(β))→(!(β)→((β)→!(β))))",
            "((((α)|(β))→!(β))→((((α)|(β))→(!(β)→((β)→!(β))))→(((α)|(β))→((β)→!(β)))))",
            "((((α)|(β))→(!(β)→((β)→!(β))))→(((α)|(β))→((β)→!(β))))",
            "(((α)|(β))→((β)→!(β)))",
            "!(α)",
            "(!(α)→(((α)|(β))→!(α)))",
            "(((α)|(β))→!(α))",
            "(!(α)→((β)→!(α)))",
            "((!(α)→((β)→!(α)))→(((α)|(β))→(!(α)→((β)→!(α)))))",
            "(((α)|(β))→(!(α)→((β)→!(α))))",
            "((((α)|(β))→!(α))→((((α)|(β))→(!(α)→((β)→!(α))))→(((α)|(β))→((β)→!(α)))))",
            "((((α)|(β))→(!(α)→((β)→!(α))))→(((α)|(β))→((β)→!(α))))",
            "(((α)|(β))→((β)→!(α)))",
            "((β)→((β)→(β)))",
            "(((β)→((β)→(β)))→(((α)|(β))→((β)→((β)→(β)))))",
            "(((α)|(β))→((β)→((β)→(β))))",
            "((β)→(((β)→(β))→(β)))",
            "(((β)→(((β)→(β))→(β)))→(((α)|(β))→((β)→(((β)→(β))→(β)))))",
            "(((α)|(β))→((β)→(((β)→(β))→(β))))",
            "(((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β))))",
            "((((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β))))→(((α)|(β))→(((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β))))))",
            "(((α)|(β))→(((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β)))))",
            "((((α)|(β))→((β)→((β)→(β))))→((((α)|(β))→(((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β)))))→(((α)|(β))→(((β)→(((β)→(β))→(β)))→((β)→(β))))))",
            "((((α)|(β))→(((β)→((β)→(β)))→(((β)→(((β)→(β))→(β)))→((β)→(β)))))→(((α)|(β))→(((β)→(((β)→(β))→(β)))→((β)→(β)))))",
            "(((α)|(β))→(((β)→(((β)→(β))→(β)))→((β)→(β))))",
            "((((α)|(β))→((β)→(((β)→(β))→(β))))→((((α)|(β))→(((β)→(((β)→(β))→(β)))→((β)→(β))))→(((α)|(β))→((β)→(β)))))",
            "((((α)|(β))→(((β)→(((β)→(β))→(β)))→((β)→(β))))→(((α)|(β))→((β)→(β))))",
            "(((α)|(β))→((β)→(β)))",
            "((!(α)→(β))→((!(α)→!(β))→!!(α)))",
            "(((!(α)→(β))→((!(α)→!(β))→!!(α)))→(((α)|(β))→((!(α)→(β))→((!(α)→!(β))→!!(α)))))",
            "(((α)|(β))→((!(α)→(β))→((!(α)→!(β))→!!(α))))",
            "(((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))",
            "((((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))→(((α)|(β))→(((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))))",
            "(((α)|(β))→(((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))))",
            "((((α)|(β))→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((((α)|(β))→(((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))))→(((α)|(β))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))))",
            "((((α)|(β))→(((!(α)→(β))→((!(α)→!(β))→!!(α)))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))))→(((α)|(β))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))))",
            "(((α)|(β))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))",
            "((β)→(!(α)→(β)))",
            "(((β)→(!(α)→(β)))→(((α)|(β))→((β)→(!(α)→(β)))))",
            "(((α)|(β))→((β)→(!(α)→(β))))",
            "(((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β)))))",
            "((((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β)))))→(((α)|(β))→(((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β)))))))",
            "(((α)|(β))→(((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β))))))",
            "((((α)|(β))→((β)→(!(α)→(β))))→((((α)|(β))→(((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β))))))→(((α)|(β))→((β)→((β)→(!(α)→(β)))))))",
            "((((α)|(β))→(((β)→(!(α)→(β)))→((β)→((β)→(!(α)→(β))))))→(((α)|(β))→((β)→((β)→(!(α)→(β))))))",
            "(((α)|(β))→((β)→((β)→(!(α)→(β)))))",
            "(((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β)))))",
            "((((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β)))))→(((α)|(β))→(((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β)))))))",
            "(((α)|(β))→(((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β))))))",
            "((((α)|(β))→((β)→(β)))→((((α)|(β))→(((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β))))))→(((α)|(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β)))))))",
            "((((α)|(β))→(((β)→(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β))))))→(((α)|(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β))))))",
            "(((α)|(β))→(((β)→((β)→(!(α)→(β))))→((β)→(!(α)→(β)))))",
            "((β)→(!(α)→(β)))",
            "(((β)→(!(α)→(β)))→(((α)|(β))→((β)→(!(α)→(β)))))",
            "(((α)|(β))→((β)→(!(α)→(β))))",
            "(!(β)→(!(α)→!(β)))",
            "((!(β)→(!(α)→!(β)))→(((α)|(β))→(!(β)→(!(α)→!(β)))))",
            "(((α)|(β))→(!(β)→(!(α)→!(β))))",
            "((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β)))))",
            "(((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β)))))→(((α)|(β))→((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β)))))))",
            "(((α)|(β))→((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β))))))",
            "((((α)|(β))→(!(β)→(!(α)→!(β))))→((((α)|(β))→((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β))))))→(((α)|(β))→((β)→(!(β)→(!(α)→!(β)))))))",
            "((((α)|(β))→((!(β)→(!(α)→!(β)))→((β)→(!(β)→(!(α)→!(β))))))→(((α)|(β))→((β)→(!(β)→(!(α)→!(β))))))",
            "(((α)|(β))→((β)→(!(β)→(!(α)→!(β)))))",
            "(((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))",
            "((((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))→(((α)|(β))→(((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))))",
            "(((α)|(β))→(((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β))))))",
            "((((α)|(β))→((β)→!(β)))→((((α)|(β))→(((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β))))))→(((α)|(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))))",
            "((((α)|(β))→(((β)→!(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β))))))→(((α)|(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β))))))",
            "(((α)|(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))",
            "((((α)|(β))→((β)→(!(β)→(!(α)→!(β)))))→((((α)|(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))→(((α)|(β))→((β)→(!(α)→!(β))))))",
            "((((α)|(β))→(((β)→(!(β)→(!(α)→!(β))))→((β)→(!(α)→!(β)))))→(((α)|(β))→((β)→(!(α)→!(β)))))",
            "(((α)|(β))→((β)→(!(α)→!(β))))",
            "(((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))",
            "((((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))→(((α)|(β))→(((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))))",
            "(((α)|(β))→(((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α))))))",
            "((((α)|(β))→((β)→(!(α)→(β))))→((((α)|(β))→(((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α))))))→(((α)|(β))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))))",
            "((((α)|(β))→(((β)→(!(α)→(β)))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α))))))→(((α)|(β))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α))))))",
            "(((α)|(β))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))",
            "((((α)|(β))→((β)→((!(α)→(β))→((!(α)→!(β))→!!(α)))))→((((α)|(β))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))→(((α)|(β))→((β)→((!(α)→!(β))→!!(α))))))",
            "((((α)|(β))→(((β)→((!(α)→(β))→((!(α)→!(β))→!!(α))))→((β)→((!(α)→!(β))→!!(α)))))→(((α)|(β))→((β)→((!(α)→!(β))→!!(α)))))",
            "(((α)|(β))→((β)→((!(α)→!(β))→!!(α))))",
            "(((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))",
            "((((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))→(((α)|(β))→(((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))))",
            "(((α)|(β))→(((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α)))))",
            "((((α)|(β))→((β)→(!(α)→!(β))))→((((α)|(β))→(((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α)))))→(((α)|(β))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))))",
            "((((α)|(β))→(((β)→(!(α)→!(β)))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α)))))→(((α)|(β))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α)))))",
            "(((α)|(β))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))",
            "((((α)|(β))→((β)→((!(α)→!(β))→!!(α))))→((((α)|(β))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))→(((α)|(β))→((β)→!!(α)))))",
            "((((α)|(β))→(((β)→((!(α)→!(β))→!!(α)))→((β)→!!(α))))→(((α)|(β))→((β)→!!(α))))",
            "(((α)|(β))→((β)→!!(α)))",
            "(!!(α)→(α))",
            "((!!(α)→(α))→(((α)|(β))→(!!(α)→(α))))",
            "(((α)|(β))→(!!(α)→(α)))",
            "((!!(α)→(α))→((β)→(!!(α)→(α))))",
            "(((!!(α)→(α))→((β)→(!!(α)→(α))))→(((α)|(β))→((!!(α)→(α))→((β)→(!!(α)→(α))))))",
            "(((α)|(β))→((!!(α)→(α))→((β)→(!!(α)→(α)))))",
            "((((α)|(β))→(!!(α)→(α)))→((((α)|(β))→((!!(α)→(α))→((β)→(!!(α)→(α)))))→(((α)|(β))→((β)→(!!(α)→(α))))))",
            "((((α)|(β))→((!!(α)→(α))→((β)→(!!(α)→(α)))))→(((α)|(β))→((β)→(!!(α)→(α)))))",
            "(((α)|(β))→((β)→(!!(α)→(α))))",
            "(((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α))))",
            "((((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α))))→(((α)|(β))→(((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α))))))",
            "(((α)|(β))→(((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α)))))",
            "((((α)|(β))→((β)→!!(α)))→((((α)|(β))→(((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α)))))→(((α)|(β))→(((β)→(!!(α)→(α)))→((β)→(α))))))",
            "((((α)|(β))→(((β)→!!(α))→(((β)→(!!(α)→(α)))→((β)→(α)))))→(((α)|(β))→(((β)→(!!(α)→(α)))→((β)→(α)))))",
            "(((α)|(β))→(((β)→(!!(α)→(α)))→((β)→(α))))",
            "((((α)|(β))→((β)→(!!(α)→(α))))→((((α)|(β))→(((β)→(!!(α)→(α)))→((β)→(α))))→(((α)|(β))→((β)→(α)))))",
            "((((α)|(β))→(((β)→(!!(α)→(α)))→((β)→(α))))→(((α)|(β))→((β)→(α))))",
            "(((α)|(β))→((β)→(α)))",
            "(((α)→(α))→(((β)→(α))→(((α)|(β))→(α))))",
            "((((α)→(α))→(((β)→(α))→(((α)|(β))→(α))))→(((α)|(β))→(((α)→(α))→(((β)→(α))→(((α)|(β))→(α))))))",
            "(((α)|(β))→(((α)→(α))→(((β)→(α))→(((α)|(β))→(α)))))",
            "((((α)|(β))→((α)→(α)))→((((α)|(β))→(((α)→(α))→(((β)→(α))→(((α)|(β))→(α)))))→(((α)|(β))→(((β)→(α))→(((α)|(β))→(α))))))",
            "((((α)|(β))→(((α)→(α))→(((β)→(α))→(((α)|(β))→(α)))))→(((α)|(β))→(((β)→(α))→(((α)|(β))→(α)))))",
            "(((α)|(β))→(((β)→(α))→(((α)|(β))→(α))))",
            "((((α)|(β))→((β)→(α)))→((((α)|(β))→(((β)→(α))→(((α)|(β))→(α))))→(((α)|(β))→(((α)|(β))→(α)))))",
            "((((α)|(β))→(((β)→(α))→(((α)|(β))→(α))))→(((α)|(β))→(((α)|(β))→(α))))",
            "(((α)|(β))→(((α)|(β))→(α)))",
            "((((α)|(β))→((α)|(β)))→((((α)|(β))→(((α)|(β))→(α)))→(((α)|(β))→(α))))",
            "((((α)|(β))→(((α)|(β))→(α)))→(((α)|(β))→(α)))",
            "(((α)|(β))→(α))",
            "(((α)|(β))→!(α))→!((α)|(β))",
            "!((α)|(β))"
    });

    public static final Lemma disjunctionFTLemma = new Lemma(new String[]{
            "(β)",
            "(β)→((α)|(β))",
            "(α)|(β)"
    });

    public static final Lemma disjunctionTFLemma = new Lemma(new String[]{
            "(α)",
            "(α)→((α)|(β))",
            "(α)|(β)"
    });

    public static final Lemma disjunctionTTLemma = new Lemma(new String[]{
            "(α)",
            "(α)→((α)|(β))",
            "(α)|(β)"
    });

    public static final Lemma implicationFFLemma = new Lemma(new String[]{
            "!(α)",
            "(!(α)→((α)→!(α)))",
            "((α)→!(α))",
            "!(β)",
            "(!(β)→((α)→!(β)))",
            "((α)→!(β))",
            "((α)→((α)→(α)))",
            "((α)→(((α)→(α))→(α)))",
            "(((α)→((α)→(α)))→(((α)→(((α)→(α))→(α)))→((α)→(α))))",
            "(((α)→(((α)→(α))→(α)))→((α)→(α)))",
            "((α)→(α))",
            "((!(β)→(α))→((!(β)→!(α))→!!(β)))",
            "(((!(β)→(α))→((!(β)→!(α))→!!(β)))→((α)→((!(β)→(α))→((!(β)→!(α))→!!(β)))))",
            "((α)→((!(β)→(α))→((!(β)→!(α))→!!(β))))",
            "((α)→(!(β)→(α)))",
            "(((α)→(!(β)→(α)))→((α)→((α)→(!(β)→(α)))))",
            "((α)→((α)→(!(β)→(α))))",
            "(((α)→(α))→(((α)→((α)→(!(β)→(α))))→((α)→(!(β)→(α)))))",
            "(((α)→((α)→(!(β)→(α))))→((α)→(!(β)→(α))))",
            "((α)→(!(β)→(α)))",
            "(!(α)→(!(β)→!(α)))",
            "((!(α)→(!(β)→!(α)))→((α)→(!(α)→(!(β)→!(α)))))",
            "((α)→(!(α)→(!(β)→!(α))))",
            "(((α)→!(α))→(((α)→(!(α)→(!(β)→!(α))))→((α)→(!(β)→!(α)))))",
            "(((α)→(!(α)→(!(β)→!(α))))→((α)→(!(β)→!(α))))",
            "((α)→(!(β)→!(α)))",
            "(((α)→(!(β)→(α)))→(((α)→((!(β)→(α))→((!(β)→!(α))→!!(β))))→((α)→((!(β)→!(α))→!!(β)))))",
            "(((α)→((!(β)→(α))→((!(β)→!(α))→!!(β))))→((α)→((!(β)→!(α))→!!(β))))",
            "((α)→((!(β)→!(α))→!!(β)))",
            "(((α)→(!(β)→!(α)))→(((α)→((!(β)→!(α))→!!(β)))→((α)→!!(β))))",
            "(((α)→((!(β)→!(α))→!!(β)))→((α)→!!(β)))",
            "((α)→!!(β))",
            "(!!(β)→(β))",
            "((!!(β)→(β))→((α)→(!!(β)→(β))))",
            "((α)→(!!(β)→(β)))",
            "(((α)→!!(β))→(((α)→(!!(β)→(β)))→((α)→(β))))",
            "(((α)→(!!(β)→(β)))→((α)→(β)))",
            "((α)→(β))"
    });

    public static final Lemma implicationFTLemma = new Lemma(new String[]{
            "(β)→((α)→(β))",
            "(β)",
            "(α)→(β)"
    });

    public static final Lemma implicationTFLemma = new Lemma(new String[]{
            "(α)",
            "!(β)",
            "!(β)→(((α)→(β))→!(β))",
            "((α)→(β))→!(β)",
            "((α)→(((α)→(β))→(α)))",
            "(((α)→(β))→(α))",
            "(((α)→(β))→(((α)→(β))→((α)→(β))))",
            "((((α)→(β))→(((α)→(β))→((α)→(β))))→((((α)→(β))→((((α)→(β))→((α)→(β)))→((α)→(β))))→(((α)→(β))→((α)→(β)))))",
            "((((α)→(β))→((((α)→(β))→((α)→(β)))→((α)→(β))))→(((α)→(β))→((α)→(β))))",
            "(((α)→(β))→((((α)→(β))→((α)→(β)))→((α)→(β))))",
            "(((α)→(β))→((α)→(β)))",
            "((((α)→(β))→(α))→((((α)→(β))→((α)→(β)))→(((α)→(β))→(β))))",
            "((((α)→(β))→((α)→(β)))→(((α)→(β))→(β)))",
            "(((α)→(β))→(β))",
            "(((α)→(β))→(β))→((((α)→(β))→!(β))→!((α)→(β)))",
            "(((α)→(β))→!(β))→!((α)→(β))",
            "!((α)→(β))"
    });

    public static final Lemma implicationTTLemma = new Lemma(new String[]{
            "(β)→((α)→(β))",
            "(β)",
            "(α)→(β)"
    });

    public static final Lemma negationFLemma = new Lemma(new String[]{
            "!(α)"
    });

    public static final Lemma negationTLemma = new Lemma(new String[]{
            "(α)",
            "(α)→(!!!(α)→(α))",
            "!!!(α)→(α)",
            "!!!(α)→!(α)",
            "(!!!(α)→(α))→((!!!(α)→!(α))→!!!!(α))",
            "(!!!(α)→!(α))→!!!!(α)",
            "!!!!(α)",
            "!!!!(α)→!!(α)",
            "!!(α)"
    });

    public static final Lemma wowLemma = new Lemma(new String[]{
            "(β)→(α)",
            "!(β)→(α)",
            "((β)→(α))→((!(β)→(α))→(((β)|!(β))→(α)))",
            "(!(β)→(α))→(((β)|!(β))→(α))",
            "((β)|!(β))→(α)",
            "(α)"
    });

    public static String[] negationReplace(Logical alpha, boolean alphaValue) {
        if (!alphaValue) {
            return negationFLemma.replace(LogicUtil.createReplacing(alpha));
        } else {
            return negationTLemma.replace(LogicUtil.createReplacing(alpha));
        }
    }

    public static String[] conjunctionReplace(Logical alpha, boolean alphaValue, Logical beta, boolean betaValue) {
        if (!alphaValue && !betaValue) {
            return conjunctionFFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!alphaValue) {
            return conjunctionFTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!betaValue) {
            return conjunctionTFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else {
            return conjunctionTTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        }
    }

    public static String[] implicationReplace(Logical alpha, boolean alphaValue, Logical beta, boolean betaValue) {
        if (!alphaValue && !betaValue) {
            return implicationFFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!alphaValue) {
            return implicationFTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!betaValue) {
            return implicationTFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else {
            return implicationTTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        }
    }

    public static String[] disjunctionReplace(Logical alpha, boolean alphaValue, Logical beta, boolean betaValue) {
        if (!alphaValue && !betaValue) {
            return disjunctionFFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!alphaValue) {
            return disjunctionFTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else if (!betaValue) {
            return disjunctionTFLemma.replace(LogicUtil.createReplacing(alpha, beta));
        } else {
            return disjunctionTTLemma.replace(LogicUtil.createReplacing(alpha, beta));
        }
    }

    public static final Lemma notNotALemma = new Lemma(new String[]{
            "α",
            "(α→(!α→α))",
            "(!α→α)",
            "(!α→(!α→!α))",
            "((!α→(!α→!α))→((!α→((!α→!α)→!α))→(!α→!α)))",
            "((!α→((!α→!α)→!α))→(!α→!α))",
            "(!α→((!α→!α)→!α))",
            "(!α→!α)",
            "((!α→α)→((!α→!α)→!!α))",
            "((!α→!α)→!!α)",
            "!!α"
    });

    public static final Lemma notNotTenthAxiomLemma = new Lemma(new String[]{
            "(α→(!!α→α))",
            "((α→(!!α→α))→(!(!!α→α)→(α→(!!α→α))))",
            "(!(!!α→α)→(α→(!!α→α)))",
            "(!(!!α→α)→(α→!(!!α→α)))",
            "(!(!!α→α)→(!(!!α→α)→!(!!α→α)))",
            "(!(!!α→α)→((!(!!α→α)→!(!!α→α))→!(!!α→α)))",
            "((!(!!α→α)→(!(!!α→α)→!(!!α→α)))→((!(!!α→α)→((!(!!α→α)→!(!!α→α))→!(!!α→α)))→(!(!!α→α)→!(!!α→α))))",
            "((!(!!α→α)→((!(!!α→α)→!(!!α→α))→!(!!α→α)))→(!(!!α→α)→!(!!α→α)))",
            "(!(!!α→α)→!(!!α→α))",
            "((α→(!!α→α))→((α→!(!!α→α))→!α))",
            "(((α→(!!α→α))→((α→!(!!α→α))→!α))→(!(!!α→α)→((α→(!!α→α))→((α→!(!!α→α))→!α))))",
            "(!(!!α→α)→((α→(!!α→α))→((α→!(!!α→α))→!α)))",
            "((!(!!α→α)→(α→(!!α→α)))→((!(!!α→α)→((α→(!!α→α))→((α→!(!!α→α))→!α)))→(!(!!α→α)→((α→!(!!α→α))→!α))))",
            "((!(!!α→α)→((α→(!!α→α))→((α→!(!!α→α))→!α)))→(!(!!α→α)→((α→!(!!α→α))→!α)))",
            "(!(!!α→α)→((α→!(!!α→α))→!α))",
            "((!(!!α→α)→(α→!(!!α→α)))→((!(!!α→α)→((α→!(!!α→α))→!α))→(!(!!α→α)→!α)))",
            "((!(!!α→α)→((α→!(!!α→α))→!α))→(!(!!α→α)→!α))",
            "(!(!!α→α)→!α)",
            "(!α→(!!α→α))",
            "((!α→(!!α→α))→(!(!!α→α)→(!α→(!!α→α))))",
            "(!(!!α→α)→(!α→(!!α→α)))",
            "((!(!!α→α)→!α)→((!(!!α→α)→(!α→(!!α→α)))→(!(!!α→α)→(!!α→α))))",
            "((!(!!α→α)→(!α→(!!α→α)))→(!(!!α→α)→(!!α→α)))",
            "(!(!!α→α)→(!!α→α))",
            "((!(!!α→α)→(!!α→α))→((!(!!α→α)→!(!!α→α))→!!(!!α→α)))",
            "((!(!!α→α)→!(!!α→α))→!!(!!α→α))",
            "!!(!!α→α)"
    });

    public static final Lemma notNotModusPonensLemma = new Lemma(new String[]{
            "(!β→((α→β)→!β))",
            "(!β→(α→!β))",
            "((!β→(α→!β))→(!β→(!β→(α→!β))))",
            "(!β→(!β→(α→!β)))",
            "((!β→(α→!β))→((α→β)→(!β→(α→!β))))",
            "(((!β→(α→!β))→((α→β)→(!β→(α→!β))))→(!β→((!β→(α→!β))→((α→β)→(!β→(α→!β))))))",
            "(!β→((!β→(α→!β))→((α→β)→(!β→(α→!β)))))",
            "((!β→(!β→(α→!β)))→((!β→((!β→(α→!β))→((α→β)→(!β→(α→!β)))))→(!β→((α→β)→(!β→(α→!β))))))",
            "((!β→((!β→(α→!β))→((α→β)→(!β→(α→!β)))))→(!β→((α→β)→(!β→(α→!β)))))",
            "(!β→((α→β)→(!β→(α→!β))))",
            "(((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))",
            "((((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))→(!β→(((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))))",
            "(!β→(((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β)))))",
            "((!β→((α→β)→!β))→((!β→(((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β)))))→(!β→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))))",
            "((!β→(((α→β)→!β)→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β)))))→(!β→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β)))))",
            "(!β→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))",
            "((!β→((α→β)→(!β→(α→!β))))→((!β→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))→(!β→((α→β)→(α→!β)))))",
            "((!β→(((α→β)→(!β→(α→!β)))→((α→β)→(α→!β))))→(!β→((α→β)→(α→!β))))",
            "(!β→((α→β)→(α→!β)))",
            "((α→β)→((α→!β)→!α))",
            "(((α→β)→((α→!β)→!α))→(!β→((α→β)→((α→!β)→!α))))",
            "(!β→((α→β)→((α→!β)→!α)))",
            "(((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α)))",
            "((((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α)))→(!β→(((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α)))))",
            "(!β→(((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α))))",
            "((!β→((α→β)→(α→!β)))→((!β→(((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α))))→(!β→(((α→β)→((α→!β)→!α))→((α→β)→!α)))))",
            "((!β→(((α→β)→(α→!β))→(((α→β)→((α→!β)→!α))→((α→β)→!α))))→(!β→(((α→β)→((α→!β)→!α))→((α→β)→!α))))",
            "(!β→(((α→β)→((α→!β)→!α))→((α→β)→!α)))",
            "((!β→((α→β)→((α→!β)→!α)))→((!β→(((α→β)→((α→!β)→!α))→((α→β)→!α)))→(!β→((α→β)→!α))))",
            "((!β→(((α→β)→((α→!β)→!α))→((α→β)→!α)))→(!β→((α→β)→!α)))",
            "(!β→((α→β)→!α))",
            "!!α",
            "(!!α→(!β→!!α))",
            "(!β→!!α)",
            "(!!α→((α→β)→!!α))",
            "((!!α→((α→β)→!!α))→(!β→(!!α→((α→β)→!!α))))",
            "(!β→(!!α→((α→β)→!!α)))",
            "((!β→!!α)→((!β→(!!α→((α→β)→!!α)))→(!β→((α→β)→!!α))))",
            "((!β→(!!α→((α→β)→!!α)))→(!β→((α→β)→!!α)))",
            "(!β→((α→β)→!!α))",
            "(!α→(!!α→!(α→β)))",
            "((!α→(!!α→!(α→β)))→(!β→(!α→(!!α→!(α→β)))))",
            "(!β→(!α→(!!α→!(α→β))))",
            "((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β)))))",
            "(((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β)))))→(!β→((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β)))))))",
            "(!β→((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β))))))",
            "((!β→(!α→(!!α→!(α→β))))→((!β→((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β))))))→(!β→((α→β)→(!α→(!!α→!(α→β)))))))",
            "((!β→((!α→(!!α→!(α→β)))→((α→β)→(!α→(!!α→!(α→β))))))→(!β→((α→β)→(!α→(!!α→!(α→β))))))",
            "(!β→((α→β)→(!α→(!!α→!(α→β)))))",
            "(((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))",
            "((((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))→(!β→(((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))))",
            "(!β→(((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β))))))",
            "((!β→((α→β)→!α))→((!β→(((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β))))))→(!β→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))))",
            "((!β→(((α→β)→!α)→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β))))))→(!β→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β))))))",
            "(!β→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))",
            "((!β→((α→β)→(!α→(!!α→!(α→β)))))→((!β→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))→(!β→((α→β)→(!!α→!(α→β))))))",
            "((!β→(((α→β)→(!α→(!!α→!(α→β))))→((α→β)→(!!α→!(α→β)))))→(!β→((α→β)→(!!α→!(α→β)))))",
            "(!β→((α→β)→(!!α→!(α→β))))",
            "(((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))",
            "((((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))→(!β→(((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))))",
            "(!β→(((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β)))))",
            "((!β→((α→β)→!!α))→((!β→(((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β)))))→(!β→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))))",
            "((!β→(((α→β)→!!α)→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β)))))→(!β→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β)))))",
            "(!β→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))",
            "((!β→((α→β)→(!!α→!(α→β))))→((!β→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))→(!β→((α→β)→!(α→β)))))",
            "((!β→(((α→β)→(!!α→!(α→β)))→((α→β)→!(α→β))))→(!β→((α→β)→!(α→β))))",
            "(!β→((α→β)→!(α→β)))",
            "!!(α→β)",
            "(!!(α→β)→(!β→!!(α→β)))",
            "(!β→!!(α→β))",
            "(!!(α→β)→((α→β)→!!(α→β)))",
            "((!!(α→β)→((α→β)→!!(α→β)))→(!β→(!!(α→β)→((α→β)→!!(α→β)))))",
            "(!β→(!!(α→β)→((α→β)→!!(α→β))))",
            "((!β→!!(α→β))→((!β→(!!(α→β)→((α→β)→!!(α→β))))→(!β→((α→β)→!!(α→β)))))",
            "((!β→(!!(α→β)→((α→β)→!!(α→β))))→(!β→((α→β)→!!(α→β))))",
            "(!β→((α→β)→!!(α→β)))",
            "(((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β)))",
            "((((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β)))→(!β→(((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β)))))",
            "(!β→(((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β))))",
            "((!β→((α→β)→!(α→β)))→((!β→(((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β))))→(!β→(((α→β)→!!(α→β))→!(α→β)))))",
            "((!β→(((α→β)→!(α→β))→(((α→β)→!!(α→β))→!(α→β))))→(!β→(((α→β)→!!(α→β))→!(α→β))))",
            "(!β→(((α→β)→!!(α→β))→!(α→β)))",
            "((!β→((α→β)→!!(α→β)))→((!β→(((α→β)→!!(α→β))→!(α→β)))→(!β→!(α→β))))",
            "((!β→(((α→β)→!!(α→β))→!(α→β)))→(!β→!(α→β)))",
            "(!β→!(α→β))",
            "(!(α→β)→(!!(α→β)→!α))",
            "((!(α→β)→(!!(α→β)→!α))→(!β→(!(α→β)→(!!(α→β)→!α))))",
            "(!β→(!(α→β)→(!!(α→β)→!α)))",
            "((!β→!(α→β))→((!β→(!(α→β)→(!!(α→β)→!α)))→(!β→(!!(α→β)→!α))))",
            "((!β→(!(α→β)→(!!(α→β)→!α)))→(!β→(!!(α→β)→!α)))",
            "(!β→(!!(α→β)→!α))",
            "((!β→!!(α→β))→((!β→(!!(α→β)→!α))→(!β→!α)))",
            "((!β→(!!(α→β)→!α))→(!β→!α))",
            "(!β→!α)",
            "((!β→!α)→((!β→!!α)→!!β))",
            "((!β→!!α)→!!β)",
            "!!β"
    });
}
