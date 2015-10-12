package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.EmailClassifier;
import classifiers.baseClassifiers.SignatureClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SignatureClassifierEN extends SignatureClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(sign(d)*)")
                                .pattern("(by)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

    };


    public SignatureClassifierEN(){

        super(LanguageSpecificRuleList);
        keywords = "sign signature";
        name = "Signature";

    }


}
