package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ArbitrationClassifier;
import classifiers.baseClassifiers.HighlightClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ArbitrationClassifierSE extends ArbitrationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("((tings|kammar|hov)rätt(en)*|fullgörelsetalan|skiljedom(en)(s)*|tvist(en|er)*(s)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(skilje(dom(s)*)*(nämnd|förfarande|institut|regler)(s|en|t|et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("allmän")
                            .pattern("domstol"))
                    .withExtraction(TokenReplacer.WORD_SPAN),
    };

    public ArbitrationClassifierSE(){

        super(RuleList);
        name = "Tvist";
    }

}
