package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;
import parse.POSClassification;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************
 *
 *          Sentence classifier uses a regular expression, but afterwards it
 *          tries to merge incomplete sentences.
 *
 *          Currently the test is for Coordinating Conjunctions (e.g. but)

 *
 */



public abstract class SentenceClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {

            new RegExpReplacer(
                    "# Match a sentence ending in punctuation or EOS.\n" +
                        "([^.!?\\s]    # First char is non-punct, non-ws\n" +
                        "[^.!?]*      # Greedily consume up to punctuation.\n" +
                        "(?:          # Group for unrolling the loop.\n" +
                        "  [.!?]      # (special) inner punctuation ok if\n" +
                        "  (?!['\"]?\\s|$)  # not followed by ws or EOS.\n" +
                        "  [^.!?]*    # Greedily consume up to punctuation.\n" +
                        ")*           # Zero or more (special normal*)\n" +
                        "[.!?]?       # Optional ending punctuation.\n" +
                        "['\"]?       # Optional closing quote.\n" +
                        "(?=\\s|$))"),
    };


    public SentenceClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.SENTENCE, FeatureTypeTree.None);
    }



    public void classify(TextFragment sentence, int currentPass){

        super.classify(sentence, currentPass);


        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "After classification: " + sentence.display(0));

        mergeIncompleteSentences(sentence);

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "After merge: " + sentence.display(0));


    }

    private void mergeIncompleteSentences(TextFragment sentence) {

        ParseNodeInterface lastChild = null;
        List<ParseNodeInterface> newChildLIst = new ArrayList<>();

        for(ParseNodeInterface thisChild : sentence.getChildren()){


            if(lastChild != null && lastChild.getNodeClass().isA(NodeClass.Type.SENTENCE) && thisChild.getNodeClass().isA(NodeClass.Type.SENTENCE)){

                System.out.println("Found two consecutive sentences. " + lastChild.getText() + "-" + thisChild.getText());

                if(thisChild.getChildren().size() == 0){
                    System.out.println("  -> Second sentences is empty. Ignoring...");
                    break;
                }

                if(thisChild.getFirstWord().getPosTag() == POSClassification.CC ){

                    // A sentence cannot start with a Coordinating Conjunction
                    // Move all children from this child to the last

                    System.out.println("  -> Merging sentences...");

                    lastChild.getChildren().addAll(thisChild.getChildren());
                    lastChild.setText(lastChild.getText() + " " + thisChild.getText());

                    // We do not re-point "last child" here. We just removed this child and the next
                    // parsed sentence may not be a sentence either os it should be merged with the
                    // same "last"


                }
                else{

                    newChildLIst.add(thisChild);
                    lastChild = thisChild;

                }

            }
            else{

                newChildLIst.add(thisChild);
                lastChild = thisChild;

            }


        }

        sentence.children = newChildLIst;

    }


}
