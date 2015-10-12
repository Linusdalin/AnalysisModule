package classifiers.baseClassifiers;

import analysis2.NodeClass;
import analysis2.ParseNodeInterface;
import analysis2.ReplacerInterface;
import analysis2.TextFragment;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import document.AbstractDefinition;
import document.AbstractDocument;
import document.AbstractProject;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

/**********************************************************************************
 *
 *              Generic classifier for definition usage
 *
 *
 *              The overridden classification method does goes through the list of definitions
 *              in the project and detects if it is a correct or dubious use of the definition.
 *
 *              This classifier is executed in the second pass of the analysis, going through the
 *              entire project as definitions can arise whereever.
 *
 */



public abstract class DefinitionRepetitionClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


    };


    protected DefinitionRepetitionClassifier(){

        super(RuleList, RuleList, NodeClass.Type.DEFINITION_USAGE,  FeatureTypeTree.DefinitionRepetition);

        relevance = 30;  // Lower the threshold. This should probably be skipped in the display

    }


    /***************************************************************************'
     *
     *          Overriding general functionality adding the matching of documents and headlined from the project
     *
     *
     * @param sentence              - the parsed text
     * @param currentPass
     */



    public void classify(TextFragment sentence, int currentPass){

        //System.out.println(" --- Classifying definitionUsage in fragment " + sentence.getText());

        super.classify(sentence, currentPass);

        // Look for the document titles in the project as they are references
        // Look for clause headlines in the document, as they are references

        AbstractProject project = sentence.getProject();

        if(project == null)
            System.out.println(" --- Project is null!");

        //System.out.println("Before usage:\n" + sentence.display(0));


        if(project != null){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG," --- There are " + project.documents.size() + " documents in project ");

            // If there is a project, let's go through it
            for(AbstractDocument document : project.documents){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Found " + document.getDefinitions().size() + " definitions in document " + document.name);


                // Go through all definitions
                for(AbstractDefinition definition : document.getDefinitions()){

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG," *** Looking for repetitions of definition " + definition.term);

                    ReplacerInterface repeated = definition.getRepeatedDefinitionRule();

                    if(repeated != null){

                        //System.out.println(" using rule " + repeated.getBody());
                        executeRule(definition.getRepeatedDefinitionRule(), sentence, currentPass);

                    }
                }
            }

        }

    }


    //TODO: Refactor this and put it in the ParseNode class

    protected int lookupNode(ParseNodeInterface node, NodeClass expected, AbstractDefinition definition) {

        int nodeCount = 0;

        System.out.println(" -- lookupNode: Comparing: " + node.getText() + " with " + definition.term);


        if(node.getNodeClass() == expected && node.getText().equalsIgnoreCase(definition.term)){

            nodeCount++;
            System.out.println(" -- Found node " + nodeCount + " of type " + node.getNodeClass() + "(" + node.getText() + ")");
        }

        for(ParseNodeInterface child : node.getChildren()){

            nodeCount += lookupNode(child, expected, definition);

        }

        System.out.println(" -- Found  " + nodeCount + " nodes)");

        return nodeCount;

    }


}
