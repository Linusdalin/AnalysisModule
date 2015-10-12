package classifiers.baseClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
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



public abstract class ClassifiedNounClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .anyWord(POSClassification.DT)
                            .anyWord(POSClassification.AJ)
                            .anyWord(POSClassification.NN))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction( 3 ),

            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .anyWord(POSClassification.DT)
                            .anyWord(POSClassification.VBN)
                            .anyWord(POSClassification.NN))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(3),


            new TokenReplacer()
                    .withCriteria(new Criteria().adjacent()
                            .anyWord(POSClassification.DT)
                            .anyWord(POSClassification.NN))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2),




    };

    protected ClassifiedNounClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.CLASSIFIED_ENTITY, FeatureTypeTree.None);
    }

}
