package analysis;

import featureTypes.FeatureTypeInterface;
import parse.AnalysisFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *          Feature Definition is the outcome of a feature classification.
 *          It matches a type and trigger data
 */

public class AnalysisOutcome {

    private List<FeatureDefinition> definitions;                     // Definitions identified in the analysis
    private List<String> keywords = new ArrayList<>();               // Keywords to add to the annotations (from all definitions)
    private AnalysisFragment fragment;                               // The fragment resulting in the analysis


    public AnalysisOutcome(){

        definitions = new ArrayList<>();

    }

    public AnalysisOutcome(List<FeatureDefinition> definitions) {

        this.definitions = definitions;

    }

    public AnalysisOutcome addDefinition(FeatureDefinition newDefinition){

        definitions.add(newDefinition);
        return this;
    }



    public String toString(){

        return ("AnalysisOutcome toString() not implemented");

    }

    public List<FeatureDefinition> getDefinitions() {

        return definitions;
    }


    /*********************************************************
     *
     *          Used to combine the result of two outcomes.
     *          (Normally one per rule)
     *
     * @param from - the other outcome to merge from
     * @return - updated object
     */

    public AnalysisOutcome merge(AnalysisOutcome from) {

        definitions.addAll(from.definitions);
        keywords.addAll(from.keywords);

        return this;
    }

    public void addFragment(AnalysisFragment fragment) {

        this.fragment = fragment;
    }

    public AnalysisFragment getFragment() {
        return fragment;
    }

    public void setDefinitions(List<FeatureDefinition> definitions) {
        this.definitions = definitions;
    }

    public boolean contains(FeatureTypeInterface type, String pattern) {

        for (FeatureDefinition definition : definitions) {

            if(definition.getType() == type  && pattern.equalsIgnoreCase(pattern))
                return true;
        }

        return false;

    }
}
