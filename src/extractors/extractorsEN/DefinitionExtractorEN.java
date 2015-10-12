package extractors.extractorsEN;

import analysis.FeatureExtractorInterface;
import extractors.DefinitionExtractor;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 */

@Deprecated
public class DefinitionExtractorEN extends DefinitionExtractor implements FeatureExtractorInterface {


    public DefinitionExtractorEN(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

                add(new TextPattern("\\(\\s*[a|an|the|this]*\\s*[\"|'](.*?)[\"|']\\s*\\)"));                       // ex: ( the "agreement") or ('agreement')
                add(new TextPattern("^\\s*\"([A-Z].*?)\"\\s{3,}\\w+"));                                            // ex: ""Agreement"    is this document"
                add(new TextPattern("^\\s*([A-Z].*?)\\s{3,}\\w+").hasHeadline("[D|d]efinition\\w*"));             // ex: Agreement    is this document"
                add(new TextPattern("^\\s*\"([A-Z].*?)\"\\s*$").lnLeftColumn());                                                  // ex: ""The Agreement""     (in the left most col)
                add(new TextPattern("^\\s*([A-Z].*?)\\s*$")
                        .lnLeftColumn().hasHeadline("[D|d]efinition\\w*"));      // ex: "The Agreement"     (in the left most col under definitions)


        }};


            patterns = new ArrayList<TextPattern>(){{

                /* Todo: Translate these to english */

                //new TextPattern(new String[] {"kallas", "(för|nedan|hädanefter|härefter)"}).veryClose().extractNextNoun();
                //new TextPattern(new String[] {"(nedan|hädanefter|härefter)", "kallad"}).veryClose().extractNextNoun();

        }};



        name = "Definitions";
        searchKeywords = new String[] {};


    }

}
