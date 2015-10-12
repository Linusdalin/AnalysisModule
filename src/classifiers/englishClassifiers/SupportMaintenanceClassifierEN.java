package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PreconditionClassifier;
import classifiers.baseClassifiers.SupportMaintenanceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SupportMaintenanceClassifierEN extends SupportMaintenanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public SupportMaintenanceClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Support&Maint";
    }


}
