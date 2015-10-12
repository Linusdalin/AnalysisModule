package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to risk by looking for payment related words and combinations
 *
 *
 */

public class WarrantyExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            new TextPattern(new String[] {"warrants", "warranty"}).anyOf(),
            new TextPattern(new String[] {"software", "warranty"}).veryClose(),
            new TextPattern(new String[] {"implied", "warranty"}).veryClose(),
            new TextPattern(new String[] {"software", "warranty"}).veryClose(),
            new TextPattern(new String[] {"as", "is"}).adjacent(),

    };



    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Warranty";
    public static final String searchKeywords[] = new String[] {"guarantees"};

    public WarrantyExtractor(){

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
