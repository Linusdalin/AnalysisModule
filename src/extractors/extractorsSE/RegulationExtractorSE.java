package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.RegulationExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class RegulationExtractorSE extends RegulationExtractor implements FeatureExtractorInterface {


    public RegulationExtractorSE(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

                add(new TextPattern("(\\((19|20)\\d\\d\\:\\d{1,6}\\))").withTag("SFS-referens"));

        }};


        patterns = new ArrayList<TextPattern>(){{

        }};



        name = "Lagar";
        searchKeywords = new String[] {};


    }

}
