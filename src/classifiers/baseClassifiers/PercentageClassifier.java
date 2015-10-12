package classifiers.baseClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
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



public abstract class PercentageClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .node(NodeClass.Type.NUMBER)
                            .word("%"))
                    .withExtraction(TokenReplacer.WORD_SPAN)


    };


    protected PercentageClassifier(ReplacerInterface[] languageSpecificRuleList){

        super(RuleList, languageSpecificRuleList, NodeClass.Type.PERCENT,  FeatureTypeTree.Percentage);
    }


}
