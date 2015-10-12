package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.MetaMatchPattern;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.LimitationOfLiabilityClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LimitationOfLiabilityClassifierSV extends LimitationOfLiabilityClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("skadestånds(ansvar|skyldighet)")
                            .pattern("(begränsning|begränsa(d|t|s))")
                            .pattern("per"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Uncapped"),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(vite)")
                            .pattern("per")
                            .pattern("(leverans)*försening(sdag|svecka|månad)|dag|vecka|månad"))

                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Uncapped"),


            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .pattern("(ansvarsbegränsning(ar|en|arna)*|skadestånds(ansvar|skyldighet))")
                            .pattern("friskrivning(ar|en|arna)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(begräns(ning|a|as|r))")
                            .pattern("ansvar"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("ansvar(ar|et)*")
                            .pattern("(in)*direkt(a)*")
                            .pattern("skad(a|or|an)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("ansvar(ar|et)*")
                            .pattern("(person|arbets)skad(a|or|an)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("krav")
                            .pattern("tredje")
                            .pattern("(part|man)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(ansvar|krav)")
                            .pattern("(begränsa[dt])")
                            .pattern("(per)")
                            .pattern("((skade(s)*)*tillfälle|gång|incident|månad|år|vecka|dag)")
                            )
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Uncapped"),


            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(direkt(a)*)")
                            .pattern("(och)")
                            .pattern("(indirekt(a)*)")
                            .pattern("skad(a|or|an)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),



            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(skada)")
                            .pattern("(och|inklusive|såväl|inkluderande)")
                            .pattern("(indirekt)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(förlust(en)*)")
                            .pattern("(av)")
                            .pattern("(data)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Uncapped"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(och|inklusive|såväl|inkluderande)")
                            .pattern("(indirekt)")
                            .pattern("(skada)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),


            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(skadestånd|ansvarsbegränsning)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


    };

    public LimitationOfLiabilityClassifierSV(){

        super(RuleList);
        name = "Ansvarsbegränsnig";

    }

}
