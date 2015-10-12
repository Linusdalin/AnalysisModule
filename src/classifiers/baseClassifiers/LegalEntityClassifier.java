package classifiers.baseClassifiers;

import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TextFragment;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class LegalEntityClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

    };

    protected LegalEntityClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.CONTRACT_TERM, FeatureTypeTree.LegalEntity);
    }


    public void classify(TextFragment sentence, int currentPass){

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " In legalEntity Classifier!");

        //System.out.println(sentence.display( 0 ));
        //System.out.println(" ******************");
        super.classify(sentence, currentPass);
    }

}
