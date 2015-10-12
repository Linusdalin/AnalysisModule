package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.DeadlineExtractor;
import extractors.baseExtractors.PeriodExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class PeriodExtractorSE extends PeriodExtractor implements FeatureExtractorInterface {


    public PeriodExtractorSE(LanguageInterface language){

        super( language );


        name = "Period";
        searchKeywords = new String[] {};

        type = FeatureTypeTree.Period;


    }


}
