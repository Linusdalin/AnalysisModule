package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.TerminationClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class TerminationClassifierSV extends TerminationClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(förtida)")
                            .pattern("(upphörande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Convenience"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(säga|sagt)")
                            .pattern("upp")
                            .pattern("(avtal(et)*|kontrakt(et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(rätt|kan)")
                            .pattern("säga")
                            .pattern("upp"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(rätt|kan)")
                            .pattern("säga[s]*")
                            .pattern("upp"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(rätt|kan)")
                            .pattern("(omedelbart|direkt)")
                            .pattern("(avbeställa|terminera|häva)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Breach"),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(omedelbart|direkt)")
                            .pattern("(rätt|kan)")
                            .pattern("(avbeställa|terminera|häva)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Breach"),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(rätt|kan)")
                            .pattern("(avbeställa|terminera|häva)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(terminer(a|at|ande|ing)|häv(a|t|ande|ning))")
                            .pattern("(avtal|kontrakt|samarbete)(et)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("((ram|avrops)*avtal(et)*s|kontrakt(et)*s)")
                            .pattern("(terminerande|avslutande|upphörande)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .stemmedPattern("(terminera|avsluta|upphör|säga(s)*|uppsägning)")
                            .pattern("((ram|avrops)*avtal(et)*|kontrakt(et)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("((ram|avrops)*avtal[ent]*|kontrakt[ent]*)")
                            .pattern("säga[s]*")
                            .pattern("upp"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),

            new TokenReplacer()
                    .withCriteria(new Criteria()
                            .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.IS_HEADLINE))
                            .anyOf()
                            .pattern("(terminer(a|at|ande|ing)|häv(a|t|ande|ning))"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),



    };

    public TerminationClassifierSV(){

        super(RuleList);
        name = "Uppsägning";
    }

}
