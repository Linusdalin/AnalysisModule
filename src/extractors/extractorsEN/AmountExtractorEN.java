package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.AmountExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

@Deprecated
public class AmountExtractorEN extends AmountExtractor implements FeatureExtractorInterface {


    public AmountExtractorEN(LanguageInterface language){

        super( language );




        name = "Value";
        searchKeywords = new String[] {"Money"};
        //type = FeatureType.AMOUNT;
        type = FeatureTypeTree.Amount;


    }


}
