package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 * The Sufficient Funds clause (also called Financing, Cash Considerations or Availability of Funds) represents that
 * the Buyer has (or shall have) the available funds to complete the agreement.
 *
 * Clause examples do not differ significantly from the standard clause. Some clause examples include this
 * representation as part of a more complex financing clause.
 *
 *      Example:
 *
 * Sufficient Funds. Buyer has [and shall have at Closing] available cash resources and financing [or other sources of
 * immediately available funds] in an amount sufficient to consummate the transactions contemplated in this Agreement
 * [including resources to pay all expenses, fees and any assumed liabilities].
 *
 *
 * Financing. Parent has cash and cash equivalents on hand and committed credit facilities (without restrictions on the
 * use of such facilities for the funding of the Transactions for such purposes or conditions precedent with respect to
 * funding) sufficient for payment of the Merger Consideration, to consummate the Merger in accordance with the terms of
 * this Agreement and to satisfy all of its own and Sub's obligations under this Agreement.

 */

public class SufficientFundsExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            // Litigation

            new TextPattern(new String[] {"sufficient", "fund"}).veryClose().withTag("Sufficient Funds"),
            new TextPattern(new String[] {"available", "resource"}).close().withTag("Sufficient Funds"),
            new TextPattern(new String[] {"consummate", "transaction"}).close().withTag("Sufficient Funds"),
            new TextPattern(new String[] {"consummate", "merger"}).close().withTag("Sufficient Funds"),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Sufficient Funds";
    public static final String searchKeywords[] = new String[] {""};


    public SufficientFundsExtractor(){

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
