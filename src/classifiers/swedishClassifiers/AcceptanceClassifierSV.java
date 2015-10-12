package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AcceptanceClassifierSV extends AcceptanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(uppdrags)")
                            .pattern("(godkännande|accepterande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)

    };

    public AcceptanceClassifierSV(){

        super(RuleList);
        name = "Acceptans";

    }

}
