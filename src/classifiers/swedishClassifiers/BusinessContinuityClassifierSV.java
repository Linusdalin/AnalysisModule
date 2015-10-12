package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.BusinessContinuityClassifier;
import classifiers.baseClassifiers.DefectClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class BusinessContinuityClassifierSV extends BusinessContinuityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .word("disaster")
                            .word("recovery"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .word("kontinuitetsplan"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

    };


    public BusinessContinuityClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Kontinuitet";
    }


}
