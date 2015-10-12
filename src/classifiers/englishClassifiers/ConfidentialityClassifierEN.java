package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ConfidentialityClassifier;
import classifiers.baseClassifiers.EmailClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ConfidentialityClassifierEN extends ConfidentialityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(confidential)")
                                .pattern("(basis)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),


            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(confidential(ity)*)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),


    };


    public ConfidentialityClassifierEN(){

        super(LanguageSpecificRuleList);
        keywords = "confidensiality, secret, disclosure";
        name = "Confidentiality";

    }


}
