package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PartyUsageClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class PartyUsageClassifierSV extends PartyUsageClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(den|de|endera|båda|alla|samtliga)")
                            .pattern("(part(en|er|erna)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("((mot)*part(en|er|erna)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("(leverantören|köparen|säljaren|beställaren|kunden)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


    };

    public PartyUsageClassifierSV(){

        super(RuleList);
        name = "Part";

    }

}
