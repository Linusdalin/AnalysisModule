package analysis2;

import classifiers.Classification;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-10-31
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */


public interface ParseNodeInterface {

    public void addChild(ParseNodeInterface child);
    List<ParseNodeInterface> getChildren();

    String display(int i);

    String getText();
    Extraction getExtraction();

    void setText(String text);
    int getTextPosition();
    int getTextLength();

    void consumeText(int textLength);

    ParseNodeInterface createPrefixNode(int length) throws AnalysisException;

    NodeClass getNodeClass();
    int getRelevance();

    WordNode getFirstWord();
    Classification getClassification();

    void extractClassifications(List<Classification> classifications, List<String> comments, int phase);

    void setChildren(List<ParseNodeInterface> newChildrenList);
    ParseMetaData getMetaData();


}
