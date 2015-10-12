package classifiers.baseClassifiers;

import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class DefectClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

    };

    protected DefectClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.CONTRACT_TERM, FeatureTypeTree.Defects);
    }

}
