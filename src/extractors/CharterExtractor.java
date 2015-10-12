package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 * The Certificate of Incorporation and Bylaws or Charter Documents clause given as representation states that the party
 * has provided a copy it is charter documents to the other party or the document are available in recent SEC filings.
 * The clause will typically provide that the charter documents are (a) complete and accurate, and (b) in full force and effect.
 *
 * Publicly traded companies will typically refer to recent SEC filings, while private companies may have additional
 * representations, further representing that the company is not in violation of its charter documents.
 *
 * The clause appears in approximately 25% of acquisition agreements. When used the language is very consistent,
 * reflected that the fact that it is rarely negotiated. *
 *
 *      Example:
 *
 *
 *
 * Public Company
 * The Company Charter and Company Bylaws as most recently filed as exhibits to the Company SEC Reports are in
 * full force and effect.

 * Private Company
 * The Company has delivered or made available to [Party] true, correct, and complete copies of the certificate of
 * incorporation and bylaws of the Company, including all amendments thereto. The Company is not in violation of its
 * certificate of incorporation or bylaws[ or equivalent organizational documents].
 *
 */

public class CharterExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {


            new TextPattern(new String[] {"certificate", "of", "incorporation"}).veryClose()
                    .and(new TextPattern(new String[] {"bylaws", "of", "company"}).veryClose()),
            new TextPattern(new String[] {"company", "charter"}).veryClose()
                    .and(new TextPattern(new String[] {"company", "bylaws"}).veryClose()),


    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Charter";
    public static final String searchKeywords[] = new String[] {"incorporation", "bylaws"};


    public CharterExtractor(){

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
