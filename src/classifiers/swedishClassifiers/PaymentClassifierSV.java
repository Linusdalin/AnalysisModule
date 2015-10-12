package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ExpensesClassifier;
import classifiers.baseClassifiers.PaymentClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PaymentClassifierSV extends PaymentClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(försenad|innehållen|innehålla)")
                            .pattern("betalning"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(dröj[e]*smålsränta)")
                            .pattern("betalning"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("betalningstid(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("köpeskilling(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(clearing|konto)(nummer|numret)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("konto(t)*")
                            .pattern("\\b(.)*bank(en)*\\b"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };

    public PaymentClassifierSV(){

        super(RuleList);
        name = "Betalning";

    }

}
