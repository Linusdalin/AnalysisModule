package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Ulf
 *
 *       Extracts all fragments that are related to the clause by looking for related word and PoS patterns.
 *       There are more Liability Fragments that can be extracted.
 *
 */

public class LimitationOfLiabilityExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"direct",  "damages", "any"}).allPresent()
            .and(new TextPattern(new String[]{"breach","month"}).someOf()),
            new TextPattern(new String[] {"neither",  "party"}).adjacent()
            .and(new TextPattern(new String[]{"indirect","damages"}).veryClose())
                .and(new TextPattern(new String[] {"lost","profits"}).adjacent()),
            new TextPattern(new String[] {"liability",  "limited"}).allPresent(),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Limitation of Liability";
    public static final String searchKeywords[] = new String[] {""};


    public LimitationOfLiabilityExtractor(){

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
