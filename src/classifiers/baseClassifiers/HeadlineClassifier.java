package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classification;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import featureTypes.FeatureTypeTree;

/*******************************************************************
 *
 *          Headlines are important to detect as there are other rules that are affected.
 *          (e.g. a headline may contain a number that should not be reported)
 *
 */



public abstract class HeadlineClassifier extends Classifier implements ClassifierInterface {


    private static final ReplacerInterface[] RuleList = {


    };


    public HeadlineClassifier(ReplacerInterface[] languageSpecific){

        super(RuleList, languageSpecific, NodeClass.Type.HEADLINE, FeatureTypeTree.None);
        relevance = NO_TAG;

    }

    /******************************************************************
     *
     *          Special case classification. Check that the text is the
     *          same as the headline and then extract the entire text.
     *
     *
     * @param fragment
     * @param currentPass
     */


    @Override
    public void classify(TextFragment fragment, int currentPass){

        // It is a headline if the text equals the headline meta data

        if(fragment.getText().equals(fragment.getMetaData().headline)){

            String tag = "";
            String keywords = "";

            TextSpan span = new TextSpan(fragment.getText(), 0, 1, fragment.getText());

            Extraction extraction = new Extraction(fragment.getText());

            ParseNodeInterface newNode = new SemanticNode(fragment.getText(),0, new NodeClass(NodeClass.Type.HEADLINE), extraction )
                    .withClassification(new Classification(FeatureTypeTree.Headline, tag, new Pattern(fragment.getText(), 0), new Extraction(fragment.getText()), keywords, currentPass)
                            .withRelevance(relevance))

            ;
            //newNode.setExtraction(new Extraction(fragment.getText()));


            replaceSpan(span, newNode, fragment);


        }

    }

}
