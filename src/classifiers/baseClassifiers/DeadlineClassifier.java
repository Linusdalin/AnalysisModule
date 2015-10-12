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



public abstract class DeadlineClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(after|before|latest)").node(NodeClass.Type.DATE))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("stipulated date")

    };


    protected DeadlineClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.DEADLINE, FeatureTypeTree.Deadline);

    }

}
