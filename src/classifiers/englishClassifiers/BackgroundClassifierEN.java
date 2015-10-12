package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceCriteriaClassifier;
import classifiers.baseClassifiers.BackgroundClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class BackgroundClassifierEN extends BackgroundClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(rationale|background|introduction|overview|scope|objective(s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(purpose)")
                            .pattern("(of|with)")
                            .pattern(regex_propsalEN)
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(this|the)")
                            .pattern("(document|proposal)")
                            .pattern("(meant|describes)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };


    public BackgroundClassifierEN(){

        super(LanguageSpecific);
        name = "Background";

    }

}
