package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.BackgroundClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class BackgroundClassifierSV extends BackgroundClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(bakgrund|intro(duktion)*|översikt|scope|objektiv|syfte|övergripande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),



    };


    public BackgroundClassifierSV(){

        super(LanguageSpecific);
        name = "Bakgrund";

    }

}
