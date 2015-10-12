package classifiers.baseClassifiers;

import analysis2.*;
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



public abstract class NumberClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("((\\-|)([0-9]\\d{0,2}([\\,|\\´|\\'|\\s]\\d{3})*|([1-9]\\d*))(\\.\\d{1,10})?)\\b")
                    .withExtraction(1)
                    .withTag("regular number")
    };

    protected NumberClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.NUMBER, FeatureTypeTree.Numex);
        relevance = 30;  // Lower the threshold. This should probably be skipped in the display
    }

}
