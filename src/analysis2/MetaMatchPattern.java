package analysis2;

import document.CellInfo;
import log.AnalysisLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * Date: 2014-11-04
 * Time: 09:16
 * To change this template use File | Settings | File Templates.
 */
public class MetaMatchPattern {


    public enum MetaType {

        HEADLINE,
        IS_HEADLINE,
        LEFT_COLUMN_OF_2
}


    public MetaType type;
    public String pattern;


    public MetaMatchPattern(MetaType type){

        this(type, "");
    }

    public MetaMatchPattern(MetaType type, String s){

        this.type = type;
        this.pattern = s;
    }

    public String toString(){

        return type.name();
    }

    /*************************************************************
     *
     *          Main match function for meta criteria
     *
     *
     * @param node  - analysed node
     * @return
     */


    public boolean matches(ParseNodeInterface node) {

        switch (type) {

            case IS_HEADLINE:

                //AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Matching an IS_HEADLINE meta condition!");
                return node.getText().equals(node.getMetaData().headline);


            case HEADLINE:
                //System.out.println("Matching a headline meta condition!");
                //System.out.println(" - \"" + node.getMetaData().headline + "\"");
                //System.out.println(" - \"" + pattern + "\"");

                Pattern regExpPattern = Pattern.compile(pattern, Pattern.MULTILINE | Pattern.COMMENTS);
                Matcher matcher = regExpPattern.matcher(node.getMetaData().headline);

                return(matcher.matches());

            case LEFT_COLUMN_OF_2:

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Matching a left column meta condition!");
                CellInfo cellInfo = node.getMetaData().cellInfo;

                if(cellInfo == null){
                    System.out.println(" !No CellInfo exists");
                    return false;
                }

                // LEFT Column only counts if it is the first of two

                if(cellInfo.col == 0 && cellInfo.rowWidth == 2)
                    return true;

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Column " + cellInfo.col + " is not left col (column=" + cellInfo.col + " width="+ cellInfo.rowWidth+")");
                return  false;

            default:

                AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "Type " + type.name() + " not implemented");
                return false;
        }

    }

}
