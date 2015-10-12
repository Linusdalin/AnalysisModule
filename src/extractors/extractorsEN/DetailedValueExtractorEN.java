package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.AmountExtractor;
import extractors.baseExtractors.DetailedValueExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class DetailedValueExtractorEN extends DetailedValueExtractor implements FeatureExtractorInterface {


    public DetailedValueExtractorEN(LanguageInterface language){

        super( language );




        name = "DetailedValue";
        searchKeywords = new String[] {"Value"};
        //type = FeatureType.AMOUNT;
        type = FeatureTypeTree.DetailedValue;


    }


}
