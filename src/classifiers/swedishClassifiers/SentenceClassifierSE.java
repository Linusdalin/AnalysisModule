package classifiers.swedishClassifiers;

import analysis2.ReplacerInterface;
import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.SentenceClassifier;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 *
 */



public class SentenceClassifierSE extends SentenceClassifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {

    };


    public SentenceClassifierSE(){

        super(RuleList);
        name = "Mening";
    }


    /*

    public void classify(Sentence sentence){





        for(ReplacerInterface replacer : replacerList ){

            List<TextSpan> spans = replacer.extractSpan(sentence);

            for(TextSpan span : spans){

                // Create a new date node

                ParseNodeInterface dateNode = new SemanticNode(span.getText(), span.getStartingPoint(), NodeType.DATE)
                        .withClassification(new FeatureTypeDate(), replacer.getTag());

                dateNode.setExtraction(new SemanticExtraction(span.getText()));      // TODO: Make this a numeric value

                replaceSpan(span, dateNode, sentence);

            }

        }

    }

            */


}
