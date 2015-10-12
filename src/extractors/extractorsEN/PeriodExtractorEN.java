package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.PeriodExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class PeriodExtractorEN extends PeriodExtractor implements FeatureExtractorInterface {


    public PeriodExtractorEN(LanguageInterface language){

        super( language );


        name = "Period";
        searchKeywords = new String[] {};

        type = FeatureTypeTree.Period;


    }


}
