package classifiers.classifierTests;

import analysis2.NodeClass;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-06
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class NodeAssertion {


    public final NodeClass.Type nodeType;
    public int count = 1;
    public String nodeExtraction = null;

    public NodeAssertion(NodeClass.Type nodeType){

        this.nodeType = nodeType;
    }

    NodeAssertion expectingCount(int count){

        this.count = count;
        return this;
    }

    NodeAssertion expectingNodeText(String text){

        this.nodeExtraction = text;
        return this;
    }


}
