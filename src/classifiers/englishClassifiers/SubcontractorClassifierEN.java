package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SubcontractorClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SubcontractorClassifierEN extends SubcontractorClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            // Not implemented
    };

    public SubcontractorClassifierEN(){

        super(RuleList);
        name = "Subcontractor";

    }

}