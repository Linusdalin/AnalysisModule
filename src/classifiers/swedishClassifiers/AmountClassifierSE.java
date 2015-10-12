package classifiers.swedishClassifiers;

import classifiers.ClassifierInterface;
import analysis2.ReplacerInterface;
import classifiers.baseClassifiers.AmountClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AmountClassifierSE extends AmountClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

    };


    public AmountClassifierSE(){

        super(LanguageSpecificRuleList);
        name = "Värde";

    }


}
