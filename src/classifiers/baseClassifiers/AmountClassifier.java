package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class AmountClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().pattern("(usd|\\$)").node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("USD"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().pattern("(sek|kronor|kr)").node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("SEK"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().pattern("(nok|kronor|kr)").node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("NOK"),



            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().pattern("(eur|euro|€)").node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("EUR"),


            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().pattern("(gbp|pound|£)").node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("GBP"),


            // And the other order


            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).pattern("(usd|\\$|dollar)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("USD"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).pattern("(sek|kronor|kr)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("SEK"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).word(":").word("-"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("SEK"),


            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).pattern("(eur|euro|€)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("EUR"),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent().node(NodeClass.Type.NUMBER).pattern("(gbp|pounds|pund|£)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("GBP")


    };


    protected AmountClassifier(ReplacerInterface[] languageSpecificRuleList){

        super(RuleList, languageSpecificRuleList, NodeClass.Type.AMOUNT,  FeatureTypeTree.Amount);
    }


}
