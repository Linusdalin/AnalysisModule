package classifiers.swedishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RestrictionClassifier;
import featureTypes.FeatureTypeTree;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RestrictionClassifierSV extends RestrictionClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {



            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(äger|äga|har)").pattern("(ej|inte|aldrig)").pattern("(rätt|rättighet|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("(skall|ska)").pattern("(ej|inte|aldrig)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(villkora(d|t))")
                            .pattern("(av)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),


            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(äger|äga|har)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(ej|inte|aldrig|endast|ingen)")
                            .pattern("(rätt|rättighet|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(äger|äga|har)")
                            .pattern("(ej|inte|aldrig|endast|ingen)")
                            .pattern("(rätt|rättighet|möjlighet)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(förbind(er|s|es))")
                            .pattern("(att)")
                            .pattern("(inte|ej|aldrig)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(får|skall|måste|äger)")
                            .pattern("(endast|bara|inte)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(endast|bara|inte)")
                           .pattern("(får|skall|måste|äger)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withClassification(FeatureTypeTree.RESTRICTION),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(har|hade|om)")
                            .node(NodeClass.Type.ACTOR)
                            .pattern("(inte|ej)")
                            .subordinateClause()
                            .pattern(past_actionsSV))
                    .withExtraction(TokenReplacer.WORD_SPAN),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(har|hade|om)")
                            .pattern("(inte|ej)")
                            .node(NodeClass.Type.ACTOR)
                            .subordinateClause()
                            .pattern(past_actionsSV))
                    .withExtraction(TokenReplacer.WORD_SPAN),

    };


    public RestrictionClassifierSV(){

        super(LanguageSpecific);
        name = "Begränsning";

    }

}
