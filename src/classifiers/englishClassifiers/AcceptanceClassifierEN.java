package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.AcceptanceClassifier;
import classifiers.baseClassifiers.CompensationClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class AcceptanceClassifierEN extends AcceptanceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(bind|bound)")
                            .pattern("(to|by)")
                            .pattern("(accept(ance|ing)*|reject(ing)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(perform)")
                            .pattern("(evaluation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(disqualification)")
                            .pattern("(process|bid|offer|proposal)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "evaluation")))
                    .withCriteria(new Criteria().close()
                            .pattern("evaluation"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(require(d)*)")
                            .pattern("(present)")
                            .pattern("(solution)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(make)")
                            .pattern("(award)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),
    };

    public AcceptanceClassifierEN(){

        super(RuleList);
        name = "Acceptance";

    }

}
