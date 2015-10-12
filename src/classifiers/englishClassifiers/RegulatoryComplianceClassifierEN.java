package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.LegalComplianceClassifier;
import classifiers.baseClassifiers.RegulatoryComplianceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RegulatoryComplianceClassifierEN extends RegulatoryComplianceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().allOf()
                                .pattern("(comply|adhere)")
                                .pattern("(with|to)")
                                .pattern("(applicable)")
                                .pattern("(regulation|law*)(s)*")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

            new TokenReplacer()
                        .withCriteria(new Criteria().allOf()
                                .pattern("(jurisdiction(s)*)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

    };


    public RegulatoryComplianceClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "RegulatoryCompliance";
    }


}
