package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.FulfillmentClassifier;
import classifiers.baseClassifiers.PhasesClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class FulfillmentClassifierSV extends FulfillmentClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public FulfillmentClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Leveransuppfyllning";
    }


}
