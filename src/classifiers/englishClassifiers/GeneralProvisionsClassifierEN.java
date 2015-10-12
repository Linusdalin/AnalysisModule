package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;
import classifiers.baseClassifiers.GeneralProvisionsClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class GeneralProvisionsClassifierEN extends GeneralProvisionsClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(law)")
                                .pattern("(apply|applicable)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag("applicable law"),

            new TokenReplacer()
                        .withCriteria(new Criteria().close()
                                .pattern("(apply|applicable)")
                                .pattern("(law)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag("applicable law"),

            new TokenReplacer()
                        .withCriteria(new Criteria().anyOf()
                                .pattern("(provision(s)*)")
                         )
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withTag(""),

    };

    public GeneralProvisionsClassifierEN(){

        super(RuleList);
        name = "GeneralProvisions";

    }

}
