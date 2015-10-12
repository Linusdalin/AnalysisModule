package analysis;

import document.AbstractClause;
import document.AbstractStructureItem;
import featureTypes.FeatureTypeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *          A feature Definition is one returned outcome of an analysis.
 *
 *          The Feature Definitions are grouped in the Analysis Outcome
 *
 */


public class FeatureDefinition {

    private static final int DEFAULT_SIGNIFICANCE = 80;  // Default value

    private final String name;
    private final FeatureHit hitType;
    private final String extractedPattern;
    private final List<String> keywords;
    private final FeatureActionType action;
    private FeatureTypeInterface featureType;
    private String tag = "";
    private AbstractStructureItem aClause = null;
    private int significance;

    private int pos, length;


    public FeatureDefinition(String name, FeatureHit hitType, String extractedPattern,  List<String> keywords, FeatureActionType action, FeatureTypeInterface type){

        this.name = name;
        this.hitType = hitType;
        this.extractedPattern = extractedPattern;
        this.action = action;
        this.featureType = type;
        this.keywords = keywords;
        this.significance = DEFAULT_SIGNIFICANCE;
    }


    public FeatureHit getHitType(){

        return hitType;
    }


    public boolean isMatch(){

        return hitType != FeatureHit.NO_MATCH;
    }



    public void addTag(String s) {

        tag += (tag.length() > 0 ? " " : "") + s;
    }

    public String getTag() {
        return tag;
    }

    public String getPattern() {

        return extractedPattern;
    }

    public FeatureActionType getAction() {
        return action;
    }

    public FeatureTypeInterface getType() {

        return featureType;
    }

    public List<String> getKeywords() {

        return keywords;
    }

    public void setClause(AbstractStructureItem aClause) {
        this.aClause = aClause;
    }

    public AbstractStructureItem getClause() {
        return aClause;
    }

    public String getName() {
        return name;
    }

    public void setPos(int i, int length) {
        this.pos = i;
        this.length = length;

        System.out.println("Pos = " + pos + " length = " + length );

    }

    public int getPos() {
        return pos;
    }

    public int getLength() {
        return length;
    }

    public int getSignificance() {
        return significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }

    public void addKeyword(String keyword) {

        System.out.println("Adding keyword " + keyword);

        this.keywords.add(keyword);
    }
}
