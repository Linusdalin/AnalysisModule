package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.AmountExtractor;
import extractors.baseExtractors.DetailedValueExtractor;
import featureTypes.FeatureTypeTree;
import language.LanguageInterface;
import openNLP.NLParser;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class DetailedValueExtractorSE extends DetailedValueExtractor implements FeatureExtractorInterface {


    public DetailedValueExtractorSE(LanguageInterface language){

        super(language);





        name = "Värde";
        searchKeywords = new String[] {""};


    }


}
