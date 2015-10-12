package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PrecedenceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PrecedenceClassifierSV extends PrecedenceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(motstridighet|tvetydighet)(er)*")
                            .pattern("(tolkning|tillämpning)")
                            .pattern("före"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),
    };

    public PrecedenceClassifierSV(){

        super(RuleList);
        name = "Tolkningsföreträde";

    }

}
