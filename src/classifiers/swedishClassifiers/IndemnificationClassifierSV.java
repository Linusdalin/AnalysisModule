package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.IndemnificationClassifier;
import classifiers.baseClassifiers.LimitationOfLiabilityClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class IndemnificationClassifierSV extends IndemnificationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            // Not implemented
    };

    public IndemnificationClassifierSV(){

        super(RuleList);
        name = "Kompensation";

    }

}
