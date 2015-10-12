package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
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



public class ResponsibilitiesClassifierSV extends ResponsibilitiesClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(fulla)")
                            .pattern("(ansvar)")
                    )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESPONSIBILITY),



    };


    public ResponsibilitiesClassifierSV(){

        super(LanguageSpecific);
        name = "Responsibilities";
    }

}
