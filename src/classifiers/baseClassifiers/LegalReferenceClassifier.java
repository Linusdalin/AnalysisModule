package classifiers.baseClassifiers;

import analysis2.NodeClass;
import analysis2.RegExpReplacer;
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



public abstract class LegalReferenceClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer("((19|20)\\d\\d\\:\\d{1,6})")
                    .withExtraction(1)
                    .withTag("SFS-Reference"),


    };

    protected LegalReferenceClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.REFERENCE, FeatureTypeTree.LegalReference);
    }

}
