package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.TermExtractor;
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

@Deprecated
public class AwardCriteriaExtractorEN extends AwardCriteriaExtractor implements FeatureExtractorInterface {


    public AwardCriteriaExtractorEN(){

        super();
        patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"award",  "criteria"}).adjacent().extractWordSpan());

        }};



        name = "Award Criteria";
        searchKeywords = new String[] {""};


    }

}
