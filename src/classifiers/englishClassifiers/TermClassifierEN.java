package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.PenaltiesClassifier;
import classifiers.baseClassifiers.TermClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class TermClassifierEN extends TermClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(period|time|extent)")
                                .pattern("(validity)")
                                .pattern("(contract|proposal|offer)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(bound)")
                                .pattern("(by)")
                                .pattern("(contract|proposal|offer)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

    };

    public TermClassifierEN(){

        super(RuleList);
        name = "Term";
    }

}
