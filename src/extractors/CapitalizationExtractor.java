package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 * The Capitalization clause identifies and describes all capital interests and any debt instruments with voting rights.
 * In some instances, the clause may also identify all outstanding debt, and, if so this language is typically buried
 * at the end of the clause.
 *
 * In many cases, the clause is drafted as a single lengthy paragraph, making it more difficult to quickly and
 * accuracy determine its components.
 *
 *      Example:
 *
 *
 *
 *
 *
 * Except as set forth in the Disclosure Schedule,
 *
 *  (i)     No Other Stock Reserved for Issuance. There are no outstanding or authorized options, warrants, purchase
 *          rights, subscription rights, conversion rights, exchange rights, or other contracts or commitments that could
 *          require the Company to issue, sell, or otherwise cause to become outstanding any of its capital stock.
 *
 *  (ii)    No Other Voting Interests. There are no bonds, debentures, notes or other indebtedness of the Company or
 *          any of its Subsidiaries having the right to vote (or convertible into, or exchangeable for, securities having
 *          the right to vote) on any matters on which holders of Company Common Stock may vote.
 *
 *  (iii)   No Contractual Obligations. There are no voting trusts, proxies or other similar agreements or understandings
 *          to which the Company or any Company Subsidiary is a party or by which the Company or any Company Subsidiary
 *          is bound with respect to the voting of any shares of capital stock of the Company or any Company Subsidiary.
 *
 *  (iv)    No Transfer Restrictions. There are no contractual obligations or commitments of any character restricting
 *          the transfer of, or requiring the registration for sale of, any shares of capital stock.
 *
 *  (v)     No Unpaid Dividends. There are no accrued or unpaid dividends with respect to any capital stock.
 *
 *
 */

public class CapitalizationExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private TextPattern[] patterns = {


            new TextPattern(new String[] {"stock",  "reserved"}).veryClose(),
            new TextPattern(new String[] {"outstanding"})
                    .and(new TextPattern(new String[]{"options", "warrants", "purchase", "subscription", "conversion", "exchange"}).someOf()),
            new TextPattern(new String[] {"bonds",  "debentures", "notes", "indebtedness"}).someOf(),
            new TextPattern(new String[] {"trusts",  "proxies", "agreement", "voting"}).someOf(),
            new TextPattern(new String[] {"restricting",  "transfer", }).close(),
            new TextPattern(new String[] {"accrued", "unpaid",  "dividend", }).someOf(),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Capitalization";
    public static final String searchKeywords[] = new String[] {""};


    public CapitalizationExtractor(){

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
