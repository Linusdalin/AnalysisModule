package classifiers.englishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ResponsibilitiesClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ResponsibilitiesClassifierEN extends ResponsibilitiesClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(sole|accept(s)*)")
                            .pattern("(responsibilit(y|ies))")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESPONSIBILITY),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(responsibilit(y|ies))")
                            .pattern("(of)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESPONSIBILITY),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(responsib|accountab)(le|ility)")
                            .pattern("(for|to)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESPONSIBILITY),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(solely)")
                            .pattern("(responsible)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESPONSIBILITY),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "responsibilities")))
                    .withCriteria(new Criteria().close()
                            .pattern("responsibilities"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };


    public ResponsibilitiesClassifierEN(){

        super(LanguageSpecific);
        name = "Responsibilities";
    }

}
