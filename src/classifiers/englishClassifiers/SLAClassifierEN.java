package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SLAClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SLAClassifierEN extends SLAClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("service")
                            .pattern("level(s)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),



    };


    public SLAClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "SLA";
    }


}
