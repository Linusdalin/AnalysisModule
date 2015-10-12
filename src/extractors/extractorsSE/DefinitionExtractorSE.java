package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.DefinitionExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

public class DefinitionExtractorSE extends DefinitionExtractor implements FeatureExtractorInterface {


    public DefinitionExtractorSE(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

                add(new TextPattern("\\(\\s*[a|an|the|this]*\\s*[\"|'](.*?)[\"|']\\s*\\)"));                        // ex: ( the "agreement") or ('agreement')
            add(new TextPattern("^\\s*\"([A-Z].*?)\"\\s{3,}\\w+"));                                                 // ex: ""Agreement"    is this document"
            add(new TextPattern("^\\s*([A-Z].*?)\\s{3,}\\w+").hasHeadline("([D|d]efinitioner\\w*|[B|b]egrepp)"));   // ex: Agreement    is this document"
            add(new TextPattern("^\\s*\"([A-Z].*?)\"\\s*$").lnLeftColumn());                                        // ex: ""The Agreement""     (in the left most col)
            add(new TextPattern("^\\s*([A-Z].*?)\\s*$")
                        .lnLeftColumn().hasHeadline("([D|d]efinitioner\\w*|[B|b]egrepp)"));                         // ex: "The Agreement"     (in the left most col under definitions)

            add(new TextPattern("\\s*\"(.+)\"\\savser"));                                                    // ex: ""Agreement" avser detta"

            add(new TextPattern("\\s*\"(.+)\"(.*)\\b(betydelse|betyder|ska betyda)\\b"));                     // ex: ""Agreement" skall nedan ha betydelsen

        }};


            patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"kallas", "(för|nedan|hädanefter|härefter)"}).veryClose().extractNextNoun());
                add(new TextPattern(new String[] {"(nedan|hädanefter|härefter)", "(kallad|nämnd|omnämnd)"}).veryClose().extractNextNoun());

            }};



        name = "Definition";
        searchKeywords = new String[] {};


    }

}
