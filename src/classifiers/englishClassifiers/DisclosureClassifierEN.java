package classifiers.englishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DisclosureClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DisclosureClassifierEN extends DisclosureClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            // Not implemented
    };

    public DisclosureClassifierEN(){

        super(RuleList);
        name = "Disclosure";

    }

}
