package classifiers;

import analysis2.*;
import featureTypes.FeatureTypeInterface;
import log.AnalysisLogger;
import openNLP.NLParser;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************************'
 *
 *          Main classifier. This is hte base class for all abstract classifier and language specific classifier
 *
 *
 *
 *          XXX-ClassifierEN()
 *            |
 *            +-----> XXX-Classifier(languageSpecificRules)
 *                      |
 *                      +----------> Classifier(languageSpecificRules, GenericRules)
 *
 *
 *          //TODO: It would be nice to be able to extract a larger scope than the word span
 *          //TODO: Bug! 1,200kronor is not split correctly as a number
 *          //TODO: Not implemented. Meta data is not propagated through the parse tree
 *          //TODO: Is it possible to merge Pattern and TextSpan. They are very similar
 *          // TODO: Writing it together does not work. SFS(1998:123)  two regex clashes?
 */


public abstract class Classifier implements ClassifierInterface {

    protected final static int NO_TAG       = 20;  // This value should not generate a tag
    protected final static int DONT_SHOW    = 50;  // Classifications that should be generated and searchable, but not show in the document
    protected final static int DEFAULT      = 80;  // Default value for classifications

    // Common text patterns. These may be subject to their own rules

    protected static final String regex_supplierEN = "(supplier|vendor)(s)*";        // Someone delivering
    protected static final String regex_solutionEN = "(solution|system)(s)*";        // What shall be delivered
    protected static final String regex_propsalEN = "(RFP|proposal)(s)*";            // The proposal

    protected static final String actionsSV = "(" +
         /* communicate*/       "informera|underrätta|meddela|beskriva|specificera|dokumentera|svara|redovisa|" +
         /* do         */       "utföra|erbjuda|tillhandahålla|leverera|skapa|(över)*lämna|följa|ingå|utge|erlägga|ersätta|(ut)*betala|hålla|iaktta(ga)*|arbeta|fullgöra|" +
         /* correct    */       "avhjälpa|korrigera|åtgärda|tillse|vidta|" +
         /* acknowledge */      "godkänna|reklamera|bestrida|efterkomma|bekräfta|"+
         /* support     */      "biträda|assistera"+

                                            ")";
    protected static final String past_actionsSV = "(" + actionsSV + "[t])";


    ReplacerInterface[] ruleList;
    ReplacerInterface[] languageSpecificList;
    private final NodeClass.Type nodeType;
    private final FeatureTypeInterface featureType;
    protected String keywords = "";
    protected String name = "";
    protected String description = "";
    protected int relevance = DEFAULT;
    NLParser parser;

    public Classifier(ReplacerInterface[] ruleList, ReplacerInterface[] languageSpecificRuleList, NodeClass.Type nodeType, FeatureTypeInterface featureType){

        this.ruleList = ruleList;
        this.languageSpecificList = languageSpecificRuleList;
        this.nodeType = nodeType;
        this.featureType = featureType;

    }



    public void classify(TextFragment sentence, int currentPass){

        this.parser = sentence.getParser();



        for(ReplacerInterface replacer : languageSpecificList ){

            executeRule(replacer, sentence, currentPass);
        }

        for(ReplacerInterface replacer : ruleList){

            executeRule(replacer, sentence, currentPass);
        }


    }

    protected void executeRule(ReplacerInterface replacer, ParseNodeInterface textFragment, int currentPass){

        executeRule(replacer, textFragment, currentPass, new NodeClass(this.nodeType));
    }



    protected void executeRule(ReplacerInterface replacer, ParseNodeInterface textFragment, int currentPass, NodeClass currentNodeClass){



        // Get all extractions

        //AnalysisLogger.log(Level.DEBUG, " ExecuteRule: " + featureType.getName() + " (" + replacer.getBody() + ")");

        try{

            List<TextSpan> spans = replacer.extractSpan(textFragment);

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " *** Got " + spans.size() + " spans for " + replacer.getBody());


            Pattern pattern;
            Extraction extraction;
            TextSpan extractionSpan = null;


            for(TextSpan span : spans){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** Found span: " + span.getText() + " for " + replacer.getBody() + " with group " + span.getExtractionGroup());

                if(span.getExtractionGroup() == replacer.getMainExtractionSpan()){

                    extractionSpan = span;
                }

                if(extractionSpan == null){

                    AnalysisLogger.log(AnalysisLogger.Level.FATAL, "Error extracting span " + replacer.getMainExtractionSpan() + " for fragment " + textFragment.getText());
                    System.out.println("ExtractionGroup: " + span.getExtractionGroup());
                    System.out.println("MainExtractionSpan: " + replacer.getMainExtractionSpan());
                    System.out.println("Spans: " + spans.size());
                    System.out.println("Replacer: " + replacer.getBody());

                    return;

                }

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** In execute rule. Rule: "+ replacer.getBody()+" Extracting pattern: " + extractionSpan.getText());

                pattern = new Pattern(extractionSpan.getText(), extractionSpan.getStartingPoint());
                extraction = new Extraction(extractionSpan.getText());

                if(span.getSemanticExtraction() != null){

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Getting semantic span " + span.getSemanticExtraction());
                    extraction = extraction.withSemanticExtraction(span.getSemanticExtraction());

                }

                // Decide classification. If the replacer has defined a feature type, we use it,
                // otherwise we use the default feature type set by the classifier

                FeatureTypeInterface classification = featureType;
                if(replacer.getFeatureType() != null)
                    classification = replacer.getFeatureType();

                // Create a new node


                ParseNodeInterface newNode = new SemanticNode(extractionSpan.getText(), extractionSpan.getStartingPoint(), currentNodeClass, extraction)
                        .withClassification(new Classification(classification, replacer.getTag(), pattern, extraction, keywords, currentPass)
                                .withRelevance(relevance));

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "-- node has extraction: " + newNode.getClassification().getExtraction().getSemanticExtraction());


                if(replacer.evaluateExtractionRestrictions(spans, this.parser)){

                    //System.out.println("Before replacing: \n" + textFragment.display( 0 ));

                    //newNode.setExtraction(extraction);
                    replaceSpan(extractionSpan, newNode, textFragment);
                    //System.out.println("After replacing: \n" + textFragment.display(0));
                }
                else{

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring classification due to failed restrictions");
                }

            }

        }catch(Exception e){

            AnalysisLogger.log( e , "Failing to parse text: "+ textFragment.getText()+" in replacer rule: " + replacer.getBody());
        }


    }

    /*

    private String createKeywordString(FeatureTypeInterface classification) {

        return "#" + getClassificationName() + " " +
                classification.getHierarchy() + " " +
                classification.getAkaTag() + " " +
                keywords;
    }

*/

    /***************************************************************************************************'
     *
     *          Finding the span in the sentence and replacing one or many nodes
     *          (for that span) with the new node
     *
     * @param span
     * @param newNode
     * @param originalTextFragment
     * @return
     *
     *
     *  //TODO: Add analysis error to the parse node and pass it up for later compilation in the analysis overview report
     */

    protected ParseNodeInterface replaceSpan(TextSpan span, ParseNodeInterface newNode, ParseNodeInterface originalTextFragment){

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** In replace Span for new node " + newNode.getNodeClass() + "(" + newNode.getText() + ") span:" + span.getText() + " ( pos: " + span.getStartingPoint() + ") " + originalTextFragment.getText() );

        List<ParseNodeInterface> newChildrenList = new ArrayList<>();  // The new replaced list of children
        int startReplace = -1;

        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** Going through " + originalTextFragment.getChildren().size() + " children.");


        for(ParseNodeInterface node : originalTextFragment.getChildren()){


            if(node.getNodeClass() == newNode.getNodeClass()){

                // We have found a node of the same type as the one we are trying to replace.
                // Stop looking here

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** Found existing child of type " + node.getNodeClass().getType().name() + " stop looking here");

                newChildrenList.add(node);


            }
            else if(node.getNodeClass().getType() == NodeClass.Type.SENTENCE){

                // This is a sentence. Recursively look in it to replace span
                // Then add the replaced outcome in the list of newChildrenList children

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "*** Found semantic node " + node.getNodeClass().getType().name() + " recursing");


                newChildrenList.add(replaceSpan(span, newNode, node));
                startReplace = -1;  // Stop replacing here



            }
            else{

                // As a token could match more than one pattern. (e.g. 0706-123456 is two numbers)
                // We repeat the process for each node until ready

                boolean completed = false;

                do{

                    //System.out.print("  --- Node: " + node.display(0));

                    if(startReplace >= 0){

                        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Finding Node " + node.getText() +" at position " + node.getTextPosition() + " at pattern: " + newNode.getText());

                        if(node.getTextPosition() < startReplace + newNode.getTextLength() ){

                            // In replace mode.
                            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Replace mode: position=" + node.getTextPosition() +" and end= " + (startReplace + newNode.getTextLength())+ " (" + startReplace + "+" + newNode.getTextLength() + ")");

                            //System.out.println("Ignoring!!!!");
                            newNode.addChild(node);
                            completed = true;
                        }
                        else{

                            startReplace = -1;
                            completed = true;
                        }
                    }

                    try{

                        if(newNode.getTextPosition() > node.getTextPosition() && newNode.getTextPosition() < node.getTextPosition() + node.getTextLength() ){

                                    // The new node (pattern) is a later part of this node.

                                int diff = newNode.getTextPosition() - node.getTextPosition();

                                //System.out.println("node: " + node.getText() + "@" + node.getTextPosition() + ":" + node.getTextLength());
                                //System.out.println("newNode: " + newNode.getText() + "@" + newNode.getTextPosition() + ":" + newNode.getTextLength());
                                AnalysisLogger.log (AnalysisLogger.Level.DEBUG, newNode.getText() + " is later part of " + node.getText() + " breaking out " + diff + " characters");

                                ParseNodeInterface newChildNode = node.createPrefixNode(diff);
                                node.consumeText(diff);
                                newChildrenList.add(newChildNode);




                        }

                        //System.out.println(" --- Comparing " + node.getTextPosition() + " and " + newNode.getTextPosition());

                        if(node.getTextPosition() == newNode.getTextPosition()){

                            //System.out.println(" --- Found start!!!!");

                            // This could be part of a token. If so we split the token in a prefix token and teh rest.

                            if(node.getTextLength() > newNode.getTextLength()){

                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, newNode.getText() + " is part of " + node.getText());
                                ParseNodeInterface newChildNode = node.createPrefixNode(newNode.getTextLength());
                                node.consumeText(newNode.getTextLength());

                                newNode.addChild(newChildNode);
                                newChildrenList.add(newNode);

                                // We want to rerun the parsing with the rest of the token
                                completed = false;
                            }


                            else{

                                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Creating a new node of type " + node.getNodeClass());

                                startReplace = node.getTextPosition();
                                newNode.addChild(node);
                                newChildrenList.add(newNode);
                                completed = true;
                            }

                        }
                        else{

                            if(startReplace == -1)
                                newChildrenList.add(node);
                           completed = true;

                        }

                    }catch(AnalysisException e){

                        e.addInformation("(old node: " + node.getText() + "with length" + node.getTextLength() + " new node: " + newNode.getText() + " with length " + newNode.getTextLength() + ")");
                        AnalysisLogger.log( e );
                        completed = true;   // Abort
                    }


                }while(!completed);

            }

        }

        originalTextFragment.setChildren(newChildrenList);
        return originalTextFragment;

    }

    /*****************************************************************************
     *
     *      For each classifier its name will be the keyword
     *      for the search completion (with "#")
     *
     * @return - the name (in correct language)
     */


    @Override
    public String getClassificationName() {
        return name;
    }

    /*******************************************************************************'
     *
     *          Description is used for help texts and automatically generated documentation
     *
     *
     * @return
     */

    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public FeatureTypeInterface getType() {

        if(featureType == null){
            AnalysisLogger.log(AnalysisLogger.Level.FATAL, " FeatureType is null for classifier " + this.name);
        }

        return featureType;
    }


    protected String strip(String x){

        return x.replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
    }


}
