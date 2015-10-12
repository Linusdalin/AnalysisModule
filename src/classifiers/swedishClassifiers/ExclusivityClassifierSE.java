package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ExclusivityClassifier;
import classifiers.baseClassifiers.RiskClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ExclusivityClassifierSE extends ExclusivityClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {




            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("dedikera[t|d]").pattern("(?:[arbets]*[team|grupp])"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Exklusivitet"),


    };


    public ExclusivityClassifierSE(){

        super(LanguageSpecific);
        name = "Exklusivitet";

    }

}
