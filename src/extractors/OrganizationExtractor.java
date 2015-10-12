package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *      Extracts all fragments that are related to Organization by looking for payment related words and combinations
 *
 *      The organization clause is a standard--and typically the first representation--given by a legal entity.
 *      It has three elements stating that the entity:
 *          (a) duly organized,
 *          (b) has power to conduct its business, and
 *          (c) is duly licensed and qualified.
 *
 *      This clause is likely required and rarely will be negotiated. In some cases, it incorporates the Corporate Documentation clause.
 *
 *          //TODO: look and report exceptions to this according to the rules above
 *
 *      Example:
 *
 *          Qualified: [Party] is duly licensed or qualified to do business and is in good standing in each
 *          jurisdiction in which the nature of the business conducted by it or the character or location of
 *          the properties and assets owned or leased by it makes such licensing or qualification necessary, except
 *          where failure to be qualified, licensed or in good standing has not had and would not reasonably excepted
 *          to have a Material Adverse Effect on [Party] [or prevent or materially delay consummation of the transaction].
 *
 */

public class OrganizationExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            new TextPattern(new String[] {"duly", "licensed"}).veryClose(),
            new TextPattern(new String[] {"material", "adverse", "effect"}).veryClose(),

    };

    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Organization";
    public static final String searchKeywords[] = new String[] {"org structure"};


    public OrganizationExtractor(){

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
