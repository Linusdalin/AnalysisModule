package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RestrictionClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RestrictionClassifierEN extends RestrictionClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(has)").pattern("(no|not)").pattern("(right)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern(regex_supplierEN)
                            .pattern("(will|shall)")
                            .pattern("(no|not)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(shall)").pattern("(not|never)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(has)")
                            .node(NodeClass.Type.DEFINITION_USAGE)
                            .pattern("(no|not|never)")
                            .pattern("(right)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(without)")
                            .pattern("(prior|written)")
                            .pattern("(authori[z|s]ation|consent)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),


    };


    public RestrictionClassifierEN(){

        super(LanguageSpecific);
        name = "Restriction";
    }

}
