package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *      An End User License Agreement statement of agreement provision seeks to bind the end user to the terms of the license. However, in the case of packaged software, the end user may not see the agreement until after they have broken the seal on the product (sometimes known as "shrink-wrapped agreements"). In the case of online transactions, the user will typically be asked to click an accept button before downloading or installing software ("click-wrapped agreements" or "click through agreements").
 *
 *      The trend of incorporating terms and conditions available only in electronic form online is rapidly growing because of its convenience and ability to ensure consistent terms. In general such terms have been held by the courts to be enforceable, but as always complex grey areas exist, such as whether the person agreeing to the terms has the authority to bind a principal. *
 *          //TODO: look and report exceptions to this according to the rules above
 *
 *
 *      Example
 *
 *      YOU AGREE TO BE BOUND BY THE TERMS OF THIS EULA BY INSTALLING, COPYING, OR OTHERWISE USING THE SOFTWARE.
 *
 *      IF YOU DO NOT AGREE, DO NOT INSTALL, COPY, OR USE THE SOFTWARE; YOU MAY RETURN IT TO YOUR PLACE OF
 *      PURCHASE FOR A FULL REFUND, IF APPLICABLE.
 *
 */

public class ClickWrapExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            new TextPattern(new String[] {"agree", "install", "software"}).allPresent(),
            new TextPattern(new String[] {"accept", "break", "seal", "install", "use", "software"}).someOf(),  //TODO: Allow groups of close here (e.g "break seal")
    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Clickwrap";
    public static final String searchKeywords[] = new String[] {""};


    public ClickWrapExtractor(){

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
