package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AmountClassifier;
import classifiers.baseClassifiers.EmailClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class EmailClassifierEN extends EmailClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public EmailClassifierEN(){

        super(LanguageSpecificRuleList);
        keywords = "mail, address";
        name = "Email";

    }


}
