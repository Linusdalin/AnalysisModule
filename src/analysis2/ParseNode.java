package analysis2;

import classifiers.Classification;
import featureTypes.FeatureTypeTree;
import log.AnalysisLogger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *              A Node Object is part of a sentence tree
 *
 *
 */
public abstract class ParseNode implements ParseNodeInterface{


    protected NodeClass nodeClass;                    // The type, used in unification
    protected String text;                          // The lexical representation
    protected Extraction extraction;                // Semantic extraction from this node
    protected int relevance;                        // Relevance for the classification

    protected ParseMetaData metaData;               // Additional information on the context of the analysis


    public List<ParseNodeInterface> children;       // Node children.
    protected int textPosition;

    private Classification classification;
    private String comment;                         // Comment to the analysis, e.g. error message

    //private FeatureTypeInterface featureType = FeatureTypeTree.Unknown;
    //private String tag = "";

    public ParseNode(String text, int position){

        this.text = text;
        children = new ArrayList<>();
        this.extraction = new Extraction(text);
        this.textPosition = position;

        this.metaData = new ParseMetaData();
        this.relevance = 80;

    }

    public ParseNode(){

        this("empty", 0);
    }

    /******************************************************************
     *
     *          Adding a child to the node
     *
     *
     * @param child - node
     */


    public void addChild(ParseNodeInterface child){

        children.add(child);

    }

    @Override
    public String display(int i) {
        return "No default display...";
    }

    /********************************************************************'
     *
     *          Setting the semantic extraction
     *
     *          @param extraction - the semantic extraction for the node
     *
     */


    public void setExtraction(Extraction extraction) {

        this.extraction = extraction;
    }

    @Override
    public Extraction getExtraction() {
        return this.extraction;
    }


    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {

        this.text = text;

    }

    /******************************************************'
     *
     *          Display function for debug
     *
     * @param indentation - the start indentation (for recursive indentation)
     * @return
     */


    public String displayChildren(int indentation){

        if(children.size() > 0){

            StringBuilder s = new StringBuilder();
            for(ParseNodeInterface child : children)
                s.append(child.display(indentation + 3));

            return s.toString();
        }
        else
            return "Empty Child";
    }


    /***********************************************************
     *
     *              Generic function for the indentations
     *
     * @param s             - string to indent
     * @param indentation   - the current indentation level (in spaces)
     * @return              -  the string prepended with spaces
     */


    protected String indent(String s, int indentation) {
       StringBuilder sb = new StringBuilder();

       //Loop as many times as specified; each time add a space to the string
       for(int i=0; i < indentation; i++)
       {
           sb.append(" ");
       }

        sb.append(s);

           //Return the string
           return sb.toString();

    }


    public int getTextPosition() {

        return textPosition;
    }


    public int getTextLength() {
        return text.length();
    }


    @Override
    public void consumeText(int textLength) {

        text = text.substring(textLength);
        textPosition += textLength;
    }

    @Override
    public NodeClass getNodeClass() {
        return nodeClass;
    }

    @Override
    public int getRelevance() {
        return this.relevance;
    }

    @Override
    public List<ParseNodeInterface> getChildren() {
        return children;
    }


    public ParseNodeInterface withClassification(Classification classification) {

        this.classification = classification;

        return this;
    }

    public Classification getClassification() {

        return this.classification;
    }



    /*************************************************************************************
     *
     *          Get classifications by recursing through the node
     *          tree looking for nodes tagged with classifications and the correct phase
     *
     *
     * @param classifications
     * @param comments
     * @param currentPass
     */

    public void extractClassifications(List<Classification> classifications, List<String> comments, int currentPass) {

        Classification thisClassification = this.getClassification();

        if(thisClassification != null && thisClassification.getType() != FeatureTypeTree.None){

            // This node has a classification
            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " *** Found a classification " + thisClassification.display());

            //Ignore if it is not part of the current pass

            if(thisClassification.getPass() != currentPass){

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring " + thisClassification.display() + " as it is from pass " + classification.getPass());

            }
            else{

                // Check if it is part of another classification.

                if(!ignoreAsPartOf(thisClassification, classifications)){

                    classifications.add(thisClassification);

                }
                else{

                    AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Ignoring " + thisClassification.display() + " as part of another classification");
                }


                if(comment != null && !comment.equals(""))
                    comments.add(this.comment);

            }



        }

        // Otherwise recurse through the children.
        // This means only the classification with the highest abstraction
        // will be retrieved.

        for(ParseNodeInterface child : children){

            child.extractClassifications(classifications, comments, currentPass);
        }

    }

    /*******************************************************************************************
     *
     *              Ignore
     *
     *              If a classification is part of another classification we shall not add it.
     *
     *              Go through existing classifications and see if there is a match.
     *
     *
     * @param thisClassification  - the classification to test
     * @param classifications     - previous classifications
     * @return                    - true if the classification should be ignored
     *
     *              NOTE:   This works because the traverse is top down in the node tree. A higher
     *                      abstraction fragment is added to the list before the lower classification.
     */

    private boolean ignoreAsPartOf(Classification thisClassification, List<Classification> classifications) {

        for(Classification existingClassification : classifications){

            if(thisClassification.getType().isPartOf(existingClassification.getType()))
                return true;

        }

        return false;
    }


    @Override
    public void setChildren(List<ParseNodeInterface> newChildrenList) {

        this.children = newChildrenList;
    }


    /*************************************************************************************'
     *
     *          Adding new classifications.
     *
     *          //TODO: Uniqueness check not implemented.
     *
     *
     * @param classification
     * @param classifications
     */


    private void addIfExists(Classification classification, List<Classification> classifications) {

        if(classification != null)
            classifications.add(classification);

    }




    protected String displayClassification() {

        if(classification == null)
            return "(No classification)";

        return classification.display();

    }


    /*****************************************************************'
     *
     *          Get first word will recursively look for a WordNode in the tree.
     *
     *
     * @return - the first node that is a word
     */



    @Override
    public WordNode getFirstWord() {

        if(this.getNodeClass().isA(NodeClass.Type.WORD))
            return (WordNode)this;

        if(getChildren().size() == 0)
            return null;

        return getChildren().get(0).getFirstWord();

    }


    @Override
    public ParseMetaData getMetaData() {
        return metaData;
    }




}
