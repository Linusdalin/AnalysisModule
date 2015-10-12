package extractors.baseExtractors;

import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import featureTypes.FeatureTypeTree;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *       Extracts a percentage
 *
 */

@Deprecated
public abstract class PercentageExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    // Name and special keywords that will be used to tag the fragment


    public PercentageExtractor(){

        super();
        //type = FeatureType.PERCENTAGE;
        type = FeatureTypeTree.Percentage;

        regExpList = new ArrayList<TextPattern>() {{

                add(new TextPattern("(\\d*[0-9](|.\\d*[0-9]|,\\d*[0-9])?%)"));     // ex: 10% or 10.2%

        }};



    }




}
