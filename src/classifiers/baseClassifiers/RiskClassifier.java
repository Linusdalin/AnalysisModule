package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;
import parse.POSClassification;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public abstract class RiskClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().anyWord(POSClassification.JJS))
                            .withExtraction(TokenReplacer.WORD_SPAN)
                            .withTag("Scope"),
    };

    protected RiskClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.FRAGMENT, FeatureTypeTree.Risk);
    }

}
