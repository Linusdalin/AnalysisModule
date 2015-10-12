package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 * The Litigation clause discloses all litigation proceeding, judgement and investigations. It generally contains three representations stating that there are:
 *      (1) no current proceeding;
 *      (2) no outstanding judgments; and
 *      (3) no pending investigations.
 *
 * Clause examples differ slightly in the language used, however the vast majority of clauses include all three of the standard elements with substantially similar language.
 *
 *
 *      Example:
 *
 *  There is no suit, action or proceeding pending or, to the knowledge of [Seller], threatened against [Seller] that,
 *  individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.
 *
 *
 *  There is no Litigation pending or, to the knowledge of [Purchaser], threatened that is reasonably likely to prohibit
 *  or restrain the ability of [Purchaser] to enter into this Agreement or to consummate the transactions contemplated hereby.
 *
 * The No Undisclosed Liabilities or Absence of Liabilities clause represents that all material liabilities have been
 * disclosed, generally on the Balance Sheet or Financial Statements.
 *
 * Clause examples consistently include the four-part standard clause with language varying mainly in the peripheral language,
 * such as setting minimum dollar amounts or qualifying the clause with language like "to the Company's knowledge".
 *
 * No Undisclosed Liabilities.
 *      [To the Company's knowledge], the Company has no material obligations or liabilities [in excess of (some dollar amount),
 *      individually or in the aggregate] [matured or unmatured, fixed or contingent] other than
 *          (i) those set forth or adequately provided for in the Balance Sheet,
 *          (ii) those incurred in the ordinary course of business and not required to be set forth in the Balance Sheet under GAAP,
 *          (iii) those incurred in the ordinary course of business since the Company Balance Sheet Date and consistent with past practice, and
 *          (iv) those incurred in connection with the execution of this Agreement.
 */

public class LiabilitiesExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            // Litigation

            new TextPattern(new String[] {"arbitration", "claim", "suit", "action", "proceeding"}).someOf().withTag("Litigation"),
            new TextPattern(new String[] {"litigation"}).someOf().withTag("Litigation"),
            new TextPattern(new String[] {"current", "proceeding"}).veryClose().withTag("Litigation"),
            new TextPattern(new String[] {"outstanding", "judgement"}).someOf().anyOrder().withTag("Litigation"),
            new TextPattern(new String[] {"pending", "investigation"}).someOf().anyOrder().withTag("Litigation"),
            new TextPattern(new String[] {"pending", "controversies"}).someOf().anyOrder().withTag("Litigation"),
            new TextPattern(new String[] {"investigation", "government"}).close().anyOrder().withTag("Litigation"),

            // Undisclosed Liabilities

            new TextPattern(new String[] {"material", "obligations"}).veryClose().withTag("Liabilities"),
            new TextPattern(new String[] {"liabilities"}).veryClose().withTag("Liabilities"),
            new TextPattern(new String[] {"material", "adverse", "effect"}).veryClose().withTag("Liabilities"),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Liabilities";
    public static final String searchKeywords[] = new String[] {""};


    public LiabilitiesExtractor(){

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
