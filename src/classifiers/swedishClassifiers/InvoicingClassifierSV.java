package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.InvoicingClassifier;
import classifiers.baseClassifiers.PaymentClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class InvoicingClassifierSV extends InvoicingClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("faktura[n]*")
                            .word("specificerad"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("specificerad")
                            .pattern("faktura"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("rätt")
                            .pattern("fakturera"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("fakturering"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };

    public InvoicingClassifierSV(){

        super(RuleList);
        name = "Fakturering";

    }

}
