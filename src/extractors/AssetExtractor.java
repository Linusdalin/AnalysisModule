package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 * The Title to Assets or Property clause, sometimes included in a general Property clause, warrants that the seller has good and marketable title (or valid leaseholds)
 * for all of the assets purported to be owned by the company and that the property is free from encumbrances.
 *
 * Clause examples differ primarily in the detail or generality of the warranty, the property included in the warranty and the exception of certain encumbrances.
 * Some clauses also further warrant the condition of the assets and/or their fitness for business purposes.
 *
 *      Example:
 *
 * Valid Ownership.
 *
 *          [To the Seller's knowledge], the [Seller] [is the sole and exclusive owner and] has good and marketable [and indefeasible] title to, or a valid leasehold
 *          interest in, the properties and assets it uses, located on its premises, shown on the Financial Statements, or acquired after the date thereof, free and clear of
 *          all Encumbrances, except for properties and assets disposed of in the Ordinary Course of Business since the date of the Financial Statements.
 *
 *
 * Exceptions to Encumbrances.
 *          (...free and clear of all Encumbrances,) except for
 *                  (1) any lien for current taxes not yet due and payable,
 *                  (2) [minor] liens that have arisen in the ordinary course of business and that do not [in any case or in the aggregate] materially detract
 *                      from the value of the assets subject thereto or materially impair the operations of the [Buyer], and
 *                  (3) liens described (in another section).
 *
 * Condition of Assets.
 *          [To the Seller's knowledge], each asset is in good operating condition and has been maintained and repaired [in accordance with normal industry practice],
 *          subject to normal wear and tear.
 *
 * Fitness for Purpose.
 *          Each asset is usable for business [as it is currently conducted] and suitable for the purpose for which it is currently used [and is proposed to be used].
 *
 *          // TODO: the word "lien" should be detected in a list where the parent included the word "asset"
 *
 *
 */

public class AssetExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            // Title to Asset

            new TextPattern(new String[] {"exclusive", "owner"}).veryClose().withTag("Title to Asset"),
            new TextPattern(new String[] {"valid", "leasehold", "interest"}).close().withTag("Title to Asset"),
            new TextPattern(new String[] {"free", "clear", "encumbrance"}).close().withTag("Title to Asset"),

            new TextPattern(new String[] {"asset", "condition"}).allPresent().withTag("Title to Asset"),
            new TextPattern(new String[] {"asset", "usable", "business"}).close().withTag("Title to Asset"),

    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Assets";
    public static final String searchKeywords[] = new String[] {""};


    public AssetExtractor(){

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
