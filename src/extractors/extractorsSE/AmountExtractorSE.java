package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.AmountExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class AmountExtractorSE extends AmountExtractor implements FeatureExtractorInterface {


    public AmountExtractorSE(LanguageInterface language){

        super(language);




        name = "Värde";
        searchKeywords = new String[] {"Pengar"};

        type = FeatureTypeTree.Amount;



    }


}
