package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AuditClassifier;
import classifiers.baseClassifiers.RiskMgmntClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RiskMgmntClassifierEN extends RiskMgmntClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            // Not implemented
    };

    public RiskMgmntClassifierEN(){

        super(RuleList);
        name = "RiskMgmnt";

    }

}
