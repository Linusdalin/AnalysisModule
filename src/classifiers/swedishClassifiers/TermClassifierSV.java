package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.TermClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class TermClassifierSV extends TermClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("avtalsperiod"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("avtal(et|en)*")
                            .word("gäller"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(förlänga|förlängning)")
                            .pattern("(avtal(et|en|speriod[s]*)*|kontrakt(et|en|speriod[s]*)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("förlängning"),

    };


    public TermClassifierSV(){

        super(RuleList);
        name = "Avtalsperiod";
    }

}
