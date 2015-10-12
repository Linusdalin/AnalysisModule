package classifiers.baseClassifiers;

import analysis2.*;
import classifiers.Classifier;
import classifiers.ClassifierInterface;
import document.AbstractDefinition;
import document.AbstractDocument;
import document.AbstractProject;
import document.DefinitionType;
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



public abstract class DefinitionUsageClassifier extends Classifier implements ClassifierInterface {

    private static final ReplacerInterface[] RuleList = {


    };


    protected DefinitionUsageClassifier(){

        super(RuleList, RuleList, NodeClass.Type.DEFINITION_USAGE,  FeatureTypeTree.DEFINITION_USAGE);

    }


    /***************************************************************************'
     *
     *          Overriding general functionality adding the matching of documents and headlined from the project
     *
     *
     * @param sentence              - the parsed text
     * @param incorrectForLanguage  - the name of the tag for incorrect definition usage in the respective language
     * @param correctForLanguage   - the name of the tag for a document reference in the respective language
     * @param currentPass
     */



    public void classify(TextFragment sentence, String incorrectForLanguage, String correctForLanguage,  int currentPass, String[] nonDefinable){

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

                    //AnalysisLogger.log(AnalysisLogger.Level.INFO, " --- Usage: Looking for " + definition.term + "(type: "+ definition.getType().name()+") definitions in fragment " + sentence.getText());


                    if(definition.getFragmentNo() == sentence.getFragmentNo()){

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring looking for definition usage of " + definition.term + " as this is the definition source");
                        continue;

                    }


                    if(isNonDefinable(definition, nonDefinable)){

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring looking for definition usage of " + definition.term + " as this is a non definable word");
                        continue;

                    }

                    NodeClass nodeClass = getNodeClassFromDefinitionType(definition.getType());

                    executeRule(definition.getCorrectDefinitionRule(), sentence, currentPass, nodeClass);

                    // Now try without the case sensitive to see if we can find an incorrect usage

                    executeRule(definition.getIncorrectDefinitionRule(), sentence, currentPass, nodeClass);

                }
            }

        }

    }

    private NodeClass getNodeClassFromDefinitionType(DefinitionType type) {

        switch (type) {

            case OBJECT:
                return new NodeClass(NodeClass.Type.OBJECT);
            case ACTOR:
                return new NodeClass(NodeClass.Type.ACTOR)
                        .withAttribute(NodeClass.NodeAttribute.NON_RECIPROCAL);
            case RECIPROCAL_ACTOR:
                return new NodeClass(NodeClass.Type.ACTOR);
        }

        return new NodeClass(NodeClass.Type.DEFINITION_USAGE);
    }

    /****************************************************
     *
     *              Go through the language specific exclusion list to avoid
     *              highlighting the classification of common words
     *
     * @param definition
     * @return
     */

    private boolean isNonDefinable(AbstractDefinition definition, String[] nonClassifiable) {

        if(nonClassifiable == null){

            AnalysisLogger.log(AnalysisLogger.Level.WARNING, "nonClassifiable words not defined!!!");
            return false;
        }

        for (String word : nonClassifiable) {


            if(definition == null){

                AnalysisLogger.log(AnalysisLogger.Level.WARNING, "definition is null!!!");

            }else
              if(definition.term.equalsIgnoreCase(word))
                 return true;
        }

        return false;

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
