package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PresigningClassifier;
import classifiers.baseClassifiers.SLAClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SLAClassifierSV extends SLAClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("servicenivå(klass)*(n|er|en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("supportärenden")
                            .pattern("besvara(de|s)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("avtalad")
                            .pattern("tillgänglighet"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

    };


    public SLAClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "SLA";
    }


}
