package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 The Subsidiaries clause represents what other businesses the disclosing party owns or controls.

 No Subsidiaries.
 The Company does not own any interest in any corporation, partnership or other entity [other than those previously disclosed by the Company].

 Disclosed Subsidiaries.
 (a) Disclosure. The Disclosure Schedule lists each Subsidiary and identifies the jurisdiction of formation and names of the officers and directors of each Subsidiary.
 (b) Ownership. The Company owns, directly or indirectly, of record and beneficially all of the outstanding equity interests of each Subsidiary, free and clear of all Encumbrances.
 (c) Organization, Qualification and Authority. Each Subsidiary is duly incorporated, validly existing and in good standing under the Laws of its jurisdiction of formation and is duly qualified and in good standing in each jurisdiction in which the nature of its business or the ownership or leasing of its properties makes such qualification or authorization necessary [other than where the failure to be qualified, authorized or in good standing would not have a Material Adverse Effect]. *
 *
 *
 *
 * Example:
 *
 *
 *

        1.1 Disclosure

        The Disclosure Schedule lists each Subsidiary and identifies the jurisdiction of formation and names of the officers and directors of each Subsidiary.
        1.2 Organization and Power

        Each Subsidiary is a corporation, partnership or limited liability company duly incorporated or organized, validly existing and in good standing under the laws of its jurisdiction of incorporation or organization.
        1.3 Authority

        Each Subsidiary has all requisite corporate power and authority to own, operate, lease and encumber its properties and carry on its business as currently conducted, except as would not, individually or in the aggregate, have a Material Adverse Effect.
        1.4 Qualification

        Each Subsidiary is duly licensed or qualified to do business as a foreign corporation, partnership or limited liability company, as applicable, in each other jurisdiction in which the character of its properties or in which the transaction of its business makes such qualification necessary, except where the failure to be so licensed or qualified would not, individually or in the aggregate, have a Material Adverse Effect.
        1.5 Capitalization

        All the outstanding shares of capital stock of, or other equity interests in, each Subsidiary of the Company have been validly issued and are fully paid and non-assessable and are owned, directly or indirectly, by the Company free and clear of all pledges, liens, charges, mortgages, encumbrances or security interests of any kind or nature whatsoever (collectively, "Liens"), other than Permitted Liens.
        1.6 No Other Ownership Interests

        Except for the capital stock of, or voting securities or equity interests in, its Subsidiaries, the Company does not own, directly or indirectly, any capital stock of, or voting securities or equity interests in, any corporation, partnership, joint venture, association or other entity.
        1.7 Subsidiaries' Ownership Interests

        None of the Subsidiaries own any capital stock or other securities of, or any proprietary interest in, any Person or entity.
        1.8 Information Supplied

        Copies of the organizational documents of each such Subsidiary, in each case as amended to date, have been made available to [Party], are complete and correct. No amendments are pending.
 *
 */

public class SubsidiaryExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"each", "subsidiary"}).veryClose()
                    .and(new TextPattern(new String[]{"disclosure",  "schedule"}).veryClose()),

            new TextPattern(new String[] {"each", "subsidiary"}).veryClose()
                    .and(new TextPattern(new String[]{"corporation",  "partnership", "limited liability", "incorporated"}).someOf()),
            new TextPattern(new String[] {"each", "subsidiary"}).veryClose()
                    .and(new TextPattern(new String[]{"requisite",  "corporate", "power"}).veryClose()),
            new TextPattern(new String[] {"each", "subsidiary"}).veryClose()
                    .and(new TextPattern(new String[]{"duly",  "licensed"}).veryClose()),
            new TextPattern(new String[] {"each", "subsidiary"}).veryClose()
                    .and(new TextPattern(new String[]{"share",  "stock"}).close()),
            new TextPattern(new String[] {"its", "subsidiaries"}).veryClose()
                    .and(new TextPattern(new String[]{"stock",  "securities"}).close()),
            new TextPattern(new String[] {"the", "subsidiaries"}).veryClose()
                    .and(new TextPattern(new String[]{"stock",  "securities"}).close()),
            new TextPattern(new String[] {"each", "subsidiary"}).close()
                    .and(new TextPattern(new String[]{"organizational",  "documents"}).veryClose()),


    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Subsidiary";
    public static final String searchKeywords[] = new String[] {""};


    public SubsidiaryExtractor(){

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
