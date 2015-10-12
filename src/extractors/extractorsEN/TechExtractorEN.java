package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.TechExtractor;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class TechExtractorEN extends TechExtractor implements FeatureExtractorInterface {


    public TechExtractorEN(){

        super();

        regExpList.add(new TextPattern("(data base)").withTag("System"));
        regExpList.add(new TextPattern("(service organisation|server operation|IT[- ]operation|IT[- ]support)").withTag("Organization"));


        name = "Teknisk Specifikation";
        searchKeywords = new String[] {"spec", "tech operation", "specification"};


    }


}
