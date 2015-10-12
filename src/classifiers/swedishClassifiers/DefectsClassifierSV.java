package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DefectClassifier;
import classifiers.baseClassifiers.DelaysClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DefectsClassifierSV extends DefectClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(identifiering(en)*|avhjälpa)(nde(t)*)*")
                            .pattern("(fel|defekt(en|er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };


    public DefectsClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Defekter";
    }


}
