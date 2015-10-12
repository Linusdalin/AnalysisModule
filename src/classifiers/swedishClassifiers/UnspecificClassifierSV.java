package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.RegExpReplacer;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.UnspecificClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class UnspecificClassifierSV extends UnspecificClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            // UNSPECIFIED

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().pattern("\\b(helhetsansvar|totalansvar)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().pattern("\\b(skyndsam(t|het))"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Responstid"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close().word("ansvar").pattern("(samtliga|alla)").pattern("(delar|moment)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(snarast|omedelbart)").pattern("(åtgärda)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Responstid"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().word("utan").pattern("dröj[e]*smål"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Response"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(erforderlig[at]*|rimlig[at]*|adekvat[a]*|skälig[at]*|lämplig[at]*)")
                            .pattern("(antal|storlek|utbildning)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(befintlig)")
                            .pattern("(information|dokumentation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Scope"),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(erforderlig[at]*|rimlig[at]*|adekvat[a]*|skälig[at]*|lämplig[at]*)")
                            .pattern("(framförhållning|tid|åtgärd(en|er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Response"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(erforderlig[at]*|rimlig[at]*|adekvat[a]*|skälig[at]*|lämplig[at]*)")
                            .pattern("(ersättning(ar)*|betalning|kompensation)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Payment"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(rimlig[at]*|adekvat[a]*|skälig[at]*|tillämplig[at]*)")
                            .pattern("(standard(er)*|(.)*rutiner|(.)*processer|regler|principer|delar)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Process"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(inte|ej)")
                            .pattern("begränsa[d|t]")
                            .pattern("(till|av)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(fullständig|komplett)")
                            .pattern("(dokumentation|beskrivning)"))

                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(men)")
                            .pattern("(inte|ej)")
                            .pattern("(endast|bergänsat|uteslutande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close().pattern("(också|även)").word("överskjutande"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Scope"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().pattern("standard(er)*").pattern("branch(en)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Praxis"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().pattern("(branchstandard(er|en)*|sedvänja)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Praxis"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("förvänta")
                            .pattern("sig"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Subjective"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("när")
                            .pattern("som")
                            .pattern("helst"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Subjective"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("kan")
                            .pattern("förväntas"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag("Subjective"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .word("optimal")
                            .word("prestanda"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.Unspecific)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(inte|ej)")
                            .pattern("(oväsentlig(t)*|obetylig(t)*)")
                            .pattern("(betydelse|avseende)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new RegExpReplacer("\\b(väsentligt (hänseende|betydelse)|åsidosätter villkor|tillräckliga åtgärder|fog förutsätta|bor(t|de) (förstå(tt)*|inse(tt)*))\\b")
                    .withExtraction(1)
                    .withTag(""),


    };


    public UnspecificClassifierSV(){

        super(LanguageSpecific);
        name = "osäkerhet";

    }

}
