package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ComplianceClassifier;
import classifiers.baseClassifiers.LegalComplianceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LegalComplianceClassifierEN extends LegalComplianceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {


    };


    public LegalComplianceClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "LegalCompliance";
    }


}
