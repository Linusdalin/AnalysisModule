package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;
import classifiers.baseClassifiers.GovernanceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class GovernanceClassifierEN extends GovernanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(point)")
                            .pattern("(of)")
                            .pattern("(contact)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),
    };

    public GovernanceClassifierEN(){

        super(RuleList);
        name = "Governance";

    }

}
