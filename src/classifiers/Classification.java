package classifiers;

import analysis2.Extraction;
import analysis2.Pattern;
import featureTypes.FeatureTypeInterface;

/******************************************************************
 *
 *          The classification for a node
 *
 *          This is publicly available in the API through the AnalysisOutcome.getClassifications() call
 *
 */

public class Classification {

    private final FeatureTypeInterface type;
    private final String tag;
    private final Pattern pattern;
    private Extraction extraction;
    private String keywords;
    private int significance;
    private int relevance;
    private int pass;              // From which pass does the classification come

    public static final int DEFAULT_STRONG_SIGNIFICANCE = 80;
    public static final int DEFAULT_STRONG_RELEVANCE = 80;
    public static final int DEFAULT_LOW_RELEVANCE = 20;



    public Classification(FeatureTypeInterface type, String tag, Pattern pattern, Extraction extraction, String keywords, int pass){


        this.type = type;
        this.tag = tag;
        this.pattern = pattern;
        this.extraction = extraction;
        this.keywords = keywords;
        this.significance = DEFAULT_STRONG_SIGNIFICANCE;
        this.relevance = DEFAULT_STRONG_RELEVANCE;
        this.pass = pass;
    }

    public String display() {

        if(type == null)
            return "no classification";


        return type.getName() + "(" + tag + "\"" + pattern.getText() + "\"" +"{"+ extraction.getSyntacticExtraction() + "-(" + extraction.getSemanticExtraction()+")}" + ")";

    }

    public int getSignificance(){

        return significance;
    }

    public int getRelevance(){

        return relevance;
    }


    public String getTag(){

        return tag;
    }
    public Pattern getPattern(){

        return pattern;
    }
    public String getKeywords(){

        return type.createKeywordString(keywords);
    }


    public FeatureTypeInterface getType() {
        return type;
    }

    public Extraction getExtraction() {
        return extraction;
    }


    public Classification withRelevance(int relevance){

        this.relevance = relevance;
        return this;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass){

        this.pass = pass;
    }

}
