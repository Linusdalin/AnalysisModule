package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RiskClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RiskClassifierSE extends RiskClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            // RISK

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("dedikera[t|d]").pattern("(?:[arbets]*[team|grupp])"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Exclusivity"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(fast|tak)pris(åtagande)*"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Price"),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("deponer(a|ing|ande)")
                            .pattern("(källkod(er)*)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag(""),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf().pattern("\\b(?:straffavgift[er]*|vite[n]*)\\b"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Penalty"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("väsentligt")
                            .pattern("avtalsbrott"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Breach"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("potentiellt")
                            .pattern("(avtalsbrott|intrång)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Speculative"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("indirekt(a)*")
                            .pattern("(kostnad(er)*|skada)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("all")
                            .pattern("skada")
                            .pattern("indirekt"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("inklusive")
                            .pattern("tredjepartsskada"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("inklusive")
                            .pattern("tredje")
                            .pattern("parts")
                            .pattern("skada"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Indirect"),


            // Non Reciprocal rights is a potential risk

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(begäran)")
                            .pattern("(från|av)")
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL)))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(för)")
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(tillfredställande|adekvat)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Subjective"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(förutsatt)")
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(acceptera(r)*|acceptans)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Subjective"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(ska|äger|äga|har|får|förbehåller)")
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(rätt|rättighet|rätten|möjlighet|häva|debitera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(ska|äger|äga|har|får|förbehåller)")
                            .pattern("(rätt|rättighet|rätten|möjlighet|häva|debitera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(äger)")
                            .subordinateClause()
                          .pattern("(föra)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),




            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(kan)")
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(komma|begära)")
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(kommer|förutsätter)")
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(kommer|förutsätter)")
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(äger)")
                            .subordinateClause()
                          .pattern("(föra)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(rätt)")
                            .word("för")
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL)))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(inverka(r|t|n)*)")
                            .pattern("(ej|inte)")
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(rätt|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),


            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(ej|inte)")
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(frånsäger|avstår)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .node(new NodeClass(NodeClass.Type.ACTOR)
                                    .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(tolkning)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Non-reciprocal"),

    };


    public RiskClassifierSE(){

        super(LanguageSpecific);
        name = "risk";

    }

}
