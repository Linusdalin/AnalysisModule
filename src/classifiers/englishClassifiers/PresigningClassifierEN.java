package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.FulfillmentClassifier;
import classifiers.baseClassifiers.PresigningClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PresigningClassifierEN extends PresigningClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public PresigningClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Contracting";
    }


}
