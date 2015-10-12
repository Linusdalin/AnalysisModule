package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.CompensationClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class CompensationClassifierEN extends CompensationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            //  A breakdown of pricing by cost component including full disclosure of any fixed,
            // tiered, sliding or other pricing schedules including disclosure of whether the charge is
            // applied on a per transaction, weekly, monthly, annual or other basis."


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(fixed|tiered|sliding|transaction)")
                            .pattern("(pric(e|ing)|cost)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(weekly|hourly|monthly|yearly|annual|dayly)")
                            .pattern("(pric(e|ing)|cost|charge)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(pric(e|ing)|cost|charge)")
                            .pattern("(per|for)")
                            .pattern("(transaction|week|hour|month|year|day|minute)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(pric(e|ing)|cost|charge|pricing/cost)")           // Special case for failing tokenizer
                            .pattern("(for|of)")
                            .pattern("(service|travel|expense)(s)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(estimate|provide)")
                            .pattern("(pric(e|ing)|cost|charge)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(will|shall|must|should)")
                            .pattern("(pa(y|id)|compensate(d)*|reimburse(d)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),



            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(charge|cost|price)")
                            .pattern("(of|on|for)")
                            .pattern(regex_solutionEN))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(the)")
                            .pattern("(cost|price)")
                            .pattern("(of)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(time)")
                            .pattern("(and)")
                            .pattern("(material)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),


            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("(pricing)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };

    public CompensationClassifierEN(){

        super(RuleList);
        name = "Compensation";

    }

}
