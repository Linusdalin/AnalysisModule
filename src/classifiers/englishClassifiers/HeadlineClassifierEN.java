package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AmountClassifier;
import classifiers.baseClassifiers.HeadlineClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class HeadlineClassifierEN extends HeadlineClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public HeadlineClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Headline";

    }


}
