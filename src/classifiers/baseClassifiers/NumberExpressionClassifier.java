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



public abstract class NumberExpressionClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            // The order is important here

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.NUMBER)
                            .word("(")
                            .node(NodeClass.Type.NUMBER)
                            .word(")"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Expression"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.NUMBER))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Expression"),




    };

    protected NumberExpressionClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.NUMBER_EXPRESSION, FeatureTypeTree.Numex);
        relevance = 30;  // Lower the threshold. This should probably be skipped in the display
    }

}
