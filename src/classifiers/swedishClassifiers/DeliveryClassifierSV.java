package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.DeliverablesClassifier;
import classifiers.baseClassifiers.DeliveryClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DeliveryClassifierSV extends DeliveryClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf().pattern("anbudspresentation").word("anbudsgenomgång"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Möte"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close().word("genomgång").word("anbud"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Möte"),


    };

    public DeliveryClassifierSV(){

        super(RuleList);
        name = "Leverabel";
    }

}
