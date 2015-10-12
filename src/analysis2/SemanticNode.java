package analysis2;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 *
 */

public class SemanticNode extends ParseNode implements ParseNodeInterface {


    public SemanticNode(String text, int textPosition, NodeClass type, Extraction extraction){

        super(text, textPosition);
        nodeClass = type;
        this.extraction = extraction;

    }


    public String display(int indentation){


        return indent("["+ nodeClass.toString() +"] " + StringUtils.rightPad(text, 20) + "{"+ getClassification().getExtraction().getSyntacticExtraction()+"-(" + getClassification().getExtraction().getSemanticExtraction() + ") }", indentation) +
                displayClassification() + "\n" +
                displayChildren(indentation + 3);
    }


    @Override
    public ParseNodeInterface createPrefixNode(int textLength) throws AnalysisException{

        if(textLength < 0 )
            throw new AnalysisException("Error creting prefix node. Length = " + textLength);

        SemanticNode newNode = new SemanticNode(this.text.substring(0, textLength), this.textPosition, this.nodeClass, this.extraction);
        return newNode;
    }



}
