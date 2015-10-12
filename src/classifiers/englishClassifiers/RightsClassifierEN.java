package classifiers.englishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RightsClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RightsClassifierEN extends RightsClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(have|has|ensure[s]*)")
                            .node(NodeClass.Type.DEFINITION_USAGE)
                            .pattern("(right[s]*|option[s]*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(have|has|retain[s]*|maintain[s]*|reserve(s)*)")
                                .pattern("(right|option)"))
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(is|are)")
                                .pattern("(entitled)")
                                .pattern("(to)"))
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(not)")
                                .pattern("(bound|binding|bind)")
                                .pattern("(by|(it)*self(s)*)"))
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),



            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(unlimited|unrestricted|full)")
                                .pattern("(access)"))
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),


            new RegExpReplacer("(right[s]* (of|to) (use|usage))")
                    .withExtraction(1)

    };




    public RightsClassifierEN(){

        super(LanguageSpecific);
        name = "Right";

    }

}
