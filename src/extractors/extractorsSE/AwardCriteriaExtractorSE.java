package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.AwardCriteriaExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts all fragments that are related to contract terms
 *
 *
 *
 */

public class AwardCriteriaExtractorSE extends AwardCriteriaExtractor implements FeatureExtractorInterface {


    public AwardCriteriaExtractorSE(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"tilldelningsgrund"}).anyOf().extractWordSpan());
                add(new TextPattern(new String[] {"utvärderingsmodell"}).anyOf().extractWordSpan());

        }};



        name = "Award Criteria";
        searchKeywords = new String[] {""};


    }

}
