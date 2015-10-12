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



public abstract class ContentClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

    };

    protected ContentClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.FRAGMENT, FeatureTypeTree.Content);
    }

}
