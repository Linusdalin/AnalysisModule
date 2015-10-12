package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.LegalEntityExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

@Deprecated
public class LegalEntityExtractorSE extends LegalEntityExtractor implements FeatureExtractorInterface {


    public LegalEntityExtractorSE(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

            add(new TextPattern("\\b(\\d\\d(0[1-9]|1[012])\\d\\d-\\d{4})\\b").withTag("Fysisk person"));                   // ex: 701210-1234
            add(new TextPattern("\\b(\\d\\d[23456789]\\d\\d\\d-\\d{4})\\b").withTag("Juridisk person"));                   // ex: 702210-1234

        }};


            patterns = new ArrayList<TextPattern>(){{

            }};



        name = "Entitet";
        searchKeywords = new String[] {};


    }

}
