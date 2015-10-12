package classifiers.swedishClassifiers;

import analysis2.NodeClass;
import classifiers.ClassifierInterface;
import analysis2.Criteria;
import analysis2.ReplacerInterface;
import analysis2.TokenReplacer;
import classifiers.baseClassifiers.DeadlineClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class DeadlineClassifierSV extends DeadlineClassifier implements ClassifierInterface {


    private static final ReplacerInterface[] LanguageSpecificRuleList = {

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(innan|före|senast)")
                            .node(NodeClass.Type.DATE))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Angivet datum"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .pattern("(inom|innan|senast)")
                            .node(NodeClass.Type.NUMBER_EXPRESSION)
                            .pattern("((arbets)*dag(ar)*|(kalender)*månad(er)*|(kalender)*veck(a|or)|timm(e|ar))"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Reaktion"),

            new TokenReplacer()
                    .withCriteria(new Criteria().veryClose()
                            .node(NodeClass.Type.NUMBER_EXPRESSION)
                            .pattern("((arbets)*dag(ar)*|(kalender)*månad(er)*|(kalender)*veck(a|or)|timm(e|ar))")
                            .pattern("(innan|före|efter)"))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("Reaktion"),


            new TokenReplacer()
                    .withCriteria(new Criteria().allOf()
                            .pattern("uppsägningstid")
                            .node(NodeClass.Type.NUMBER_EXPRESSION))
                    .withExtraction(TokenReplacer.WORD_SPAN)
                    .withTag("uppsägning"),


    };


    public DeadlineClassifierSV(){

        super(LanguageSpecificRuleList);
        name = "Deadline";

    }

    /*

    public void classify(Sentence sentence){




        List<TextSpan> spans = replacer.extractSpan(sentence);

        for(TextSpan span : spans){

            // Create a new number node

            ParseNodeInterface numberNode = new SemanticNode(span.getText(), span.getStartingPoint(), NodeType.DEADLINE)
                    .withClassification(new FeatureTypeDeadline(), replacer.getTag());

            numberNode.setExtraction(new SemanticExtraction(span.getText()));      // TODO: Make this a numeric value

            replaceSpan(span, numberNode, sentence);

        }

    }

      */
}
