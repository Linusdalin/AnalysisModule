package classifiers.englishClassifiers;

import analysis2.Criteria;
import analysis2.NodeClass;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.RiskClassifier;
import classifiers.baseClassifiers.SentenceClassifier;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class RiskClassifierEN extends RiskClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecific = {

            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("(penalt[y|ies])"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Penalty Risk"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose().pattern("dedicated").pattern("(?:team|group|resources)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Exclusivity"),


                        // Non Reciprocal rights is a potential risk


            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .pattern("(may|can|allows|gives)")
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(charge|cancel|terminate|right)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),

            new TokenReplacer()
                    .withCriteria(new Criteria().close()
                            .node(new NodeClass(NodeClass.Type.ACTOR).withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL))
                            .pattern("(may|can|allows|gives)")
                            .pattern("(charge|cancel|terminate|right)"))
                    .withExtraction(TokenReplacer.WORD_SPAN).withTag("Non-reciprocal"),


    };


    public RiskClassifierEN(){

        super(LanguageSpecific);
        name = "Risk";

    }

}
