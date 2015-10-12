package analysis2;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************'
 *
 *      Node types in the parse tree
 *
 *
 */

public class NodeClass {

    private Type type;
    private List<NodeAttribute> attributes = new ArrayList<>();

    public NodeClass(Type type){

        this.type = type;
    }


    public NodeClass withAttribute(NodeAttribute attribute){

        //System.out.println(" !! Setting attribute " + attribute.name() + " for node class " + type.name());

        attributes.add(attribute);
        return this;
    }

    public boolean hasAttribute(NodeAttribute attribute){

        return attributes.contains(attribute);
    }

    public boolean isA(Type type){

        return this.type == type;
    }

    public Type getType() {
        return type;
    }

    public String toString(){

        String attributes = listAttributes();

        return getType().name() + ( attributes.length() > 0? "(" + attributes + ")" : "");

    }

    public String listAttributes() {

        StringBuffer description = new StringBuffer();

        for (NodeAttribute attribute : attributes) {
            description.append(attribute.name() + " ");

        }


        return description.toString();
    }

    public List<NodeAttribute> getAttributes() {

        return attributes;
    }


    public enum NodeAttribute {
        NON_RECIPROCAL
    }

    public enum Type {

    FRAGMENT,
    SENTENCE,
    HEADLINE,

    DEADLINE,

    DATE, AMOUNT,  EMAIL, URL,
    NUMBER, NUMBER_EXPRESSION,

    DEFINITION,
    DEFINITION_USAGE, ACTOR, OBJECT,

    CLASSIFIED_ENTITY,

    WORD,
    SUPERLATIVE,

    STRUCTURE,

    PERCENT, CONTRACT_TERM, REFERENCE, DOCUMENT_TERM, TECHNICAL_TERM,
    }
}
