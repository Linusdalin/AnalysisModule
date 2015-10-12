package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *      The Ownership clause in a license agreement or non-disclosure agreement states that all proprietary rights are retained by the licensing ot disclosing party. A non-diclosure agreement further states that the provision of information or other property interests does not convey any license to the information.


 (a) Proprietary Rights. [Disclosing Party][Licensor] retains all proprietary rights to the [information][Licensed Property].

 (b) License Rights. No license, express or implied, is granted other than to use the information in the manner and to the extent authorized in this agreement.

 */

public class OwnershipExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            new TextPattern(new String[] {"retains", "proprietary", "rights"}).close(),
            new TextPattern(new String[] {"license", "granted",}).allPresent(),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Ownership";
    public static final String searchKeywords[] = new String[] {"Who owns"};


    public OwnershipExtractor(){

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
