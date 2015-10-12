package classifiers.englishClassifiers;

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



public class DeliveryClassifierEN extends DeliveryClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(must|shall|should|may)")
                            .pattern("(submit(ted|ting)*|respond)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(principles|guidelines)")
                            .pattern("(response|responding|submission|submitting)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(submit|submission)")
                            .pattern("(response(s)*|proposal)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(response)")
                            .pattern("(template)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            //Response shall consist of

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(response|submission)")
                            .pattern("(must|shall|should|could)")
                            .pattern("(consist|contain|include)"))
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
                            .pattern("(please|must|shall)")
                            .pattern("(provide)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(provide|submit|seeking|requesting)")
                            .pattern("(details|proposal)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(all)")
                            .pattern("(documentation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };

    public DeliveryClassifierEN(){

        super(RuleList);
        name = "Deliverable";
    }

}
