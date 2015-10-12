package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ulf
 *
 *       Extracts all fragments that are related to the clause by looking for related word and PoS patterns.
 *
 */

public class TerminationForCauseExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"breach",  "agreement"}).allPresent()
            .and(new TextPattern(new String[]{"days","month"}).someOf()),
            new TextPattern(new String[] {"material",  "respect"}).close()
            .and(new TextPattern(new String[]{"breach"}).allPresent()),
            new TextPattern(new String[] {"may",  "terminate"}).adjacent()
            .and(new TextPattern(new String[] {"breach"}).allPresent()),
            new TextPattern(new String[] {"terminate",  "agreement"}).veryClose(),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Termination for Cause";
    public static final String searchKeywords[] = new String[] {"breach", "violation"};


    public TerminationForCauseExtractor(){

        super();

    }


    public AnalysisOutcome classify(AnalysisFragment fragment){


        AnalysisOutcome outcome = null;

        for(TextPattern p : patterns){

            outcome = new AnalysisOutcome().addDefinition(new FragmentCondition(fragment).contains(p).testClassification(FeatureActionType.CLASSIFY, type, name));

        }

        return outcome;
    }




}
