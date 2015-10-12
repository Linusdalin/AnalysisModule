package classifiers.swedishClassifiers;

import analysis2.*;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RightsClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RightsClassifierSV extends RightsClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(rättigheter)")
                    .meta(new MetaMatchPattern(MetaMatchPattern.MetaType.HEADLINE, "Rättigheter")))

                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                        .withCriteria(new Criteria().veryClose()
                                .pattern("(är)")
                                .pattern("(berättiga[dt])")
                                .pattern("(till)"))
                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                        .withCriteria(new Criteria().allOf()
                                .pattern("(ha)")
                                .pattern("(äganderätt)"))

                        .withExtraction(TokenReplacer.WORD_SPAN)
                        .withClassification(FeatureTypeTree.RIGHT),



            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(ska|äger|äga|har|får|förbehåller)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(rätt|rättighet|rätten|möjlighet|häva|debitera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ska|äger|äga|har|får|förbehåller)")
                            .pattern("(rätt|rättighet|rätten|möjlighet|häva|debitera)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(äger)")
                            .subordinateClause()
                          .pattern("(föra)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),




            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(kan)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(komma|begära)")
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(kommer|förutsätter)")
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(kommer|förutsätter)")
                            .node(NodeClass.Type.ACTOR)
                            .word("att"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(äger)")
                            .subordinateClause()
                          .pattern("(föra)"))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(rätt)")
                            .word("för")
                            .node(NodeClass.Type.ACTOR))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(inverka(r|t|n)*)")
                            .pattern("(ej|inte)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(rätt|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),


            new TokenReplacer()
                    .withCriteria(new Criteria().prettyClose()
                            .pattern("(ej|inte)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(frånsäger|avstår)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(äger|äga|har)")
                            .pattern("(rätt|rättighet|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RIGHT),

    };



    public RightsClassifierSV(){

        super(LanguageSpecific);
        name = "rättighet";

    }

}
