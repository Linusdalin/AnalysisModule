package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ComplianceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ComplianceClassifierSV extends ComplianceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public ComplianceClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Compliance";
    }


}
