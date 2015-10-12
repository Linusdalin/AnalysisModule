package analysis2;

import log.AnalysisLogger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-04
 * Time: 09:16
 * To change this template use File | Settings | File Templates.
 */
public class TokenMatchPattern {



    public enum TokenType {
        TAG,
        ATTRIBUTE,
        ANYWORD,
        WORDCLASS,
        STEMMED_REGEX,
        META,           // Meta token is matched against a defined meta data in the analysis (e.g HEADLINE)
        REGEX,
        OPTIONAL,
        DISTANCE

    }


    public String pattern;
    public TokenType type;
    public List<NodeClass.NodeAttribute> nodeAttributeRestriction;

    public String semanticRestriction = null;  // Restriction on the semantic extraction from the token


    public TokenMatchPattern(String pattern, TokenType type){

        this.type = type;
        this.pattern = pattern;
    }


    public TokenMatchPattern(NodeClass nodeClass) {

        this.type = TokenType.ATTRIBUTE;
        this.pattern = nodeClass.getType().name();
        this.nodeAttributeRestriction = nodeClass.getAttributes();

    }


    public TokenMatchPattern withSemanticRestriction(String semanticRestriction) {

        this.semanticRestriction = semanticRestriction;
        return this;
    }


    public String toString(){

        return type.name() + "(" + pattern + ")";
    }

    /*************************************************************
     *
     *          Main match function
     *
     *
     * @param node
     * @return
     *
     *
     *
     */


    public boolean matches(ParseNodeInterface node) {


        switch (type) {

            case ATTRIBUTE:

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Looking for a node with type " + pattern + " AND Attributes " + nodeAttributeRestriction +" found " + node.getNodeClass().toString() + " for node " + node.getText());

                return (pattern.equalsIgnoreCase(node.getNodeClass().getType().name())
                        && matchAttributes(node, nodeAttributeRestriction)
                        && matchRestriction( node ));



            case TAG:

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " --- Looking for a node with type " + pattern + " found " + node.getNodeClass().getType().name() + " for node " + node.getText());

                return (pattern.equalsIgnoreCase(node.getNodeClass().getType().name()) && matchRestriction( node ));


            case WORDCLASS:{

                if(node instanceof WordNode){

                    return((WordNode) node).getPosTag().is(pattern)  && matchRestriction( node );

                }
                return false;

            }

            case ANYWORD:
                return pattern.equalsIgnoreCase(node.getText())  && matchRestriction( node );

            case REGEX:  {

                Pattern regExpPattern = Pattern.compile(pattern);
                Matcher matcher = regExpPattern.matcher(node.getText().toLowerCase());
                return (matcher.matches());

            }
            case STEMMED_REGEX:   {

                Pattern regExpPattern = Pattern.compile(pattern);
                Matcher matcher;
                if(node instanceof WordNode){
                    matcher = regExpPattern.matcher(((WordNode)node).getStem());
                }
                else{
                    matcher = regExpPattern.matcher(node.getText().toLowerCase());
                }

                return (matcher.matches());
            }

            case META:

                return matchMeta(pattern, node);

            default:

                System.out.println("Type " + type + " not implemented");
                return false;
        }

    }

    private boolean matchAttributes(ParseNodeInterface node, List<NodeClass.NodeAttribute> attributeRestriction) {

        boolean matchesAll = true;

        if(attributeRestriction == null){

            AnalysisLogger.log(AnalysisLogger.Level.DEBUG, " No attributes found ");
            return false;
        }

        for (NodeClass.NodeAttribute nodeAttribute : attributeRestriction) {

            matchesAll &= node.getNodeClass().hasAttribute(nodeAttribute);

        }

        return matchesAll;

    }

    private boolean matchRestriction(ParseNodeInterface node) {

        if(semanticRestriction != null){

            if(node == null){

                System.out.println("No node found. cant match " + semanticRestriction);
                return false;
            }


            if(node.getExtraction() == null){

                System.out.println("No extraction from node " + node.getNodeClass().getType().name() + " cant match " + semanticRestriction);
                return false;
            }

            if(node.getExtraction().getSemanticExtraction() == null){

                System.out.println("No SEMANTIC extraction from node " + node.getNodeClass().getType().name() + " cant match " + semanticRestriction);
                return false;
            }


            Pattern regExpPattern = Pattern.compile(semanticRestriction);
            Matcher matcher = regExpPattern.matcher(node.getExtraction().getSemanticExtraction());
            if(!matcher.matches()){

                System.out.println("Semantic restriction " + semanticRestriction + " on Node " + node.getNodeClass().getType().name() + " does not match " + node.getExtraction().getSemanticExtraction());
                return false;

            }
            return true;


        }

        return true;    // N/A

    }

    private boolean matchMeta(String pattern, ParseNodeInterface node) {

        if(pattern.equals("#HEADLINE")){

            System.out.println("Matching a headline meta condition!");
            System.out.println(" - \"" + node.getMetaData().headline + "\"");
            System.out.println(" - \"" + node.getText() + "\"");
            return node.getMetaData().headline.equalsIgnoreCase(node.getText());

        }

        System.out.println("Unknown ");

        return false;

    }


}
