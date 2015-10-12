package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.ComplianceClassifier;
import classifiers.baseClassifiers.LegalComplianceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class LegalComplianceClassifierSV extends LegalComplianceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("lagkrav"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("tillstånd"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("bemyndigande"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("dispens"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().anyOf()
                            .word("lagkrav").word("tillstånd").word("bemyndiganden").word("dispenser"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(strider|bryter)")
                            .pattern("(lag(en|ar)*|rätt\\b)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new RegExpReplacer("(\\((19|20)\\d\\d\\:\\d{1,6}\\))")
                    .withExtraction(1)
                    .withTag("SFS-referens"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("datainspektionen")
                            .pattern("dnr"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withSemanticExtraction(2)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(personuppgiftslag(en)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(PUL)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("information"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(MSB)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),



    };


    public LegalComplianceClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "ComplianceLag";
    }


}
