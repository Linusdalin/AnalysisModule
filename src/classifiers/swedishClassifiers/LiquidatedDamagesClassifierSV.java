package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DamagesClassifier;
import classifiers.baseClassifiers.LiquidatedDamagesClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LiquidatedDamagesClassifierSV extends LiquidatedDamagesClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("\\b(?:straffavgift[er]*|vite[n]*)\\b"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("vite"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(försening[s]*|straff)vite[n]*\\b"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("vite"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(avdrag)")
                            .pattern("(ersättning(en)*|pris(et)*avgift(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("prisavdrag"),

    };

    public LiquidatedDamagesClassifierSV(){

        super(RuleList);
        name = "Vite";

    }

}
