package analysis2;

import org.apache.commons.lang.StringUtils;
import parse.POSClassification;

/**
 *              Instance of a parse node representing a word
 *
 *              This is the base component in the Sentence
 *
 */


public class WordNode extends ParseNode implements ParseNodeInterface {

    final POSClassification posTag;
    final String wordStem;

    public static final int DISTANCE_FOR_MAJOR = 60;
    public static final int DISTANCE_FOR_MINOR = 10;

    /****************************************************
     *
     *          Word Node
     *
     * @param text           - the token text
     * @param stem           - the word stem
     * @param textPosition   - text position in the original text
     * @param posTag         - POS tag from the analysis
     */


    public WordNode(String text, String stem, int textPosition, String posTag){

        super(text, textPosition);
        nodeClass = new NodeClass(NodeClass.Type.WORD);
        this.posTag = classify(text, posTag);
        this.wordStem = stem.toLowerCase();

    }


    public String display(int indentation){

        // No children to the word node

        return indent("[" + nodeClass.toString() + "] " + StringUtils.rightPad(text, 20) + "{" + extraction.getSyntacticExtraction() + "}" + " (Stem: "+ wordStem+", Pos:" + posTag.getDescription() + " @" + textPosition + ")\n", indentation);
    }

    /**************************************************************************'
     *
     *          creating a new node of the text in the beginning of an existing node
     *
     *
     * @param textLength
     * @return
     * @throws AnalysisException
     *
     *          //TODO: This is not stemming the word. Is it needed?
     */


    @Override
    public ParseNodeInterface createPrefixNode(int textLength) throws AnalysisException {

        if(textLength < 0)
            throw new AnalysisException("Error creating prefix node. Text =\"\" and length = " + textLength);

        WordNode newNode = new WordNode(this.text.substring(0, textLength), this.text.substring(0, textLength), this.textPosition, this.posTag.name());
        return newNode;
    }



    public static POSClassification classify(String text, String posAnalysis) {

        if(posAnalysis == null)
            return POSClassification.UNK;

        if(posAnalysis.equals(".") || posAnalysis.equals(",") ||posAnalysis.equals(";") || posAnalysis.equals(":"))     //TODO: Add more..?
            return POSClassification.IP;
        if(text.equals("'") || text.equals("\""))
            return POSClassification.QT;
        if(posAnalysis.equals("-LRB-"))
            return POSClassification.LRB;
        if(posAnalysis.equals("-RRB-"))
            return POSClassification.RRB;

        for(POSClassification c : POSClassification.values()){

            if(c.name().equalsIgnoreCase(posAnalysis))
                return c;

        }

        return POSClassification.UNK;
    }

    public int getDistanceValue(){

        if(isMinor())
            return DISTANCE_FOR_MINOR;

        return DISTANCE_FOR_MAJOR;

    }


    public boolean isMinor() {

        //System.out.println( display() );

        if(
                        posTag == POSClassification.DT
                    ||  posTag == POSClassification.CC
                    ||  posTag == POSClassification.CD
                    ||  posTag == POSClassification.EX
                    ||  posTag == POSClassification.IN
                    ||  posTag == POSClassification.LS
                    ||  posTag == POSClassification.MD
                    ||  posTag == POSClassification.PDT
                    ||  posTag == POSClassification.POS
                    ||  posTag == POSClassification.PRP
                    ||  posTag == POSClassification.PRP$
                    ||  posTag == POSClassification.RP
                    ||  posTag == POSClassification.SYM
                    ||  posTag == POSClassification.TO
                    ||  posTag == POSClassification.UH
                    ||  posTag == POSClassification.WDT
                    ||  posTag == POSClassification.WP
                    ||  posTag == POSClassification.WP$
                    ||  posTag == POSClassification.IP
                    ||  posTag == POSClassification.QT
                    ||  posTag == POSClassification.LRB
                    ||  posTag == POSClassification.VBP
                    ||  posTag == POSClassification.VBZ
                    ||  posTag == POSClassification.RRB)
            return  true;

        return false;

    }


    public POSClassification getPosTag() {
        return posTag;
    }

    public CharSequence getStem() {
        return wordStem;
    }
}
