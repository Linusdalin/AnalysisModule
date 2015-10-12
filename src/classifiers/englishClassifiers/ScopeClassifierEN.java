package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.BusinessContinuityClassifier;
import classifiers.baseClassifiers.ScopeClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ScopeClassifierEN extends ScopeClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(scope)")
                            .pattern("(of)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "scope")))
                    .withCriteria(new Criteria().close()
                            .pattern("scope"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),




    };


    public ScopeClassifierEN(){

        super(LanguageSpecificRuleList);
        name = "Scope";
    }


}
