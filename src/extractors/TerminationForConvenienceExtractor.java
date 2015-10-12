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

public class TerminationForConvenienceExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"may",  "terminate"}).adjacent()
            .and(new TextPattern(new String[]{"agreement","contract"}).someOf())
                    .and(new TextPattern(new String[]{"convenience","notice"}).allPresent()),
            new TextPattern(new String[] {"terminate",  "part"}).veryClose()
            .and(new TextPattern(new String[]{"agreement","contract"}).someOf()),
            new TextPattern(new String[] {"may",  "terminate"}).adjacent()
            .and(new TextPattern(new String[] {"breach"}).noneOf()),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Termination For Convenience";
    public static final String searchKeywords[] = new String[] {""};


    public TerminationForConvenienceExtractor(){

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
