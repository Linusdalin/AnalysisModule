package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.CompensationClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class CompensationClassifierSV extends CompensationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .word("krav")
                            .word("på")
                            .pattern("(ersättning|betalning|kompensation)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.COMPENSATION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(utan)")
                            .pattern("(extra|ytterligare|tillkommande|särskild)")
                            .pattern("(kostnat|avgift|ersättning)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(ändra)")
                            .pattern("(pris(er|erna)*)")
                            .pattern("((avtals)*perioden)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Risk"),



            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(ersättning(en|ar)*|pris(et)*)")
                            .pattern("(ingår|inklusive)")
                            .pattern("(skatt(er)*)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(ersättning(en|ar)*|pris(et)*)")
                            .pattern("(enklusive)")
                            .pattern("(skatt(er)*)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),



            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("pris(justering(en|ar)*|reduktion(en|er)*|komponent(en|er)*|list(a|or|an)*|bilag(a|or|an)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("justering"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("justering(en|ar)*")
                            .pattern("ersättning(en|ar)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("justering"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .pattern("(pris(et)*|ersättning)")
                            .pattern("per"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("justering"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(stå|svara)(r)*")
                            .pattern("för")
                            .pattern("(kostnad|ersättning)(en|ar|er)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(noll(-)*pris|prispost(en|er|erna)*)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(tim(-)*pris(et|er|erna)*)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN),


    };

    public CompensationClassifierSV(){

        super(RuleList);
        name = "Betalning";
    }

}
