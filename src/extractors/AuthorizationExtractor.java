package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *      Extracts all fragments that are related to Authorization by looking for payment related words and combinations
 *
 *      The Authorization clause contains three elements:
 *          (a) authority,
 *          (b) delivery, and
 *          (c) enforceablity.
 *
 *      The elements may be drafted as separate clauses or combined together. It is sometimes combined with the
 *      No Conflicts or No Violation clause. In fact, the different combinations and variations of the clauses
 *      at the start of the representation and warranties section frequently make these clauses the most variable
 *      in the entire agreement.
 *
 *      The clause takes two forms. The clause may state that:
 *          (a) the party has duly authorized, delivered and executed the agreement or
 *          (b) the party has the requisite authority to execute the agreement.
 *
 *      This clause is likely required and rarely will be negotiated. In some cases, it incorporates the
 *      Corporate Documentation clause.
 *
 *          //TODO: look and report exceptions to this according to the rules above
 *
 *
 *      Example —Concise Form
 *
 *      Authority: [Party] has the authority to enter into the Agreement and to perform the obligation required.
 *
 *      Example —Qualification for Stockholder Approval
 *
 *      Authority: The execution and delivery of this Agreement by the [Party] and the consummation by the [Party] of
 *      the transactions contemplated by this Agreement have been duly authorized by all necessary corporate action on
 *      the part of the [Party] and no other corporate proceedings on the part of the [Party] are necessary to authorize
 *      this Agreement or to consummate the transactions contemplated hereby, subject, in the case of the consummation
 *      of the Merger, to the obtaining of the Stockholder Approval.
 *
 */

public class AuthorizationExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            new TextPattern(new String[] {"has", "authority", "enter"}).close(),
            new TextPattern(new String[] {"enter", "into", "agreement"}).close(),
            new TextPattern(new String[] {"duly", "authorized"}).veryClose(),
            new TextPattern(new String[] {"duly", "executed", "delivered"}).close(),
            new TextPattern(new String[] {"enforceable", "in", "accordance"}).close(),
    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Authorization";
    public static final String searchKeywords[] = new String[] {""};


    public AuthorizationExtractor(){

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
