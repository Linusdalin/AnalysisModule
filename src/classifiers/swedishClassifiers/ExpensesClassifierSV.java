package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ExpensesClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class ExpensesClassifierSV extends ExpensesClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(utgift|kostnad)(en|er)*")
                            .pattern("(för|relaterade)")
                            .pattern("(genom|ut)förande(t)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(svara|stå)(r)*")
                            .pattern("(för)")
                            .pattern("(utgift|kostnad)(en|er)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };

    public ExpensesClassifierSV(){

        super(RuleList);
        name = "Kostnader";

    }

}
