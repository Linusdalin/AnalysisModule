package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.GovernanceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class GovernanceClassifierSV extends GovernanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(uppföljning)")
                            .pattern("(avtal(et)*|kontrakt(et)*|åtagande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("åtgärd(er)*")
                            .word("följas")
                            .word("upp"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(kontaktperson(en|er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

        };


    public GovernanceClassifierSV(){

        super(RuleList);
        name = "Styrning";

    }

}
