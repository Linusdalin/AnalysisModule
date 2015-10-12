package extractors.extractorsSE;

import analysis.AnalysisOutcome;
import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.ReferenceExtractor;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *
 * Examples:
 *
 *     enligt punkt 27.1
     motsvaras av avsnitt 6 i Förfrågningsunderlaget
     mellan detta Ramavtal, Avropsavtal
     framgår av Bilaga 1-2
     enligt punkt 6.1.6
     i enlighet med punkt 11
     i Bilaga 2
     enligt Bilaga 5
     i enlighet med Bilaga 4
     som anges i punkt 27.1
     Bilaga 1:
     enligt villkoren i denna punkt
     enligt 2 kap. 6 §
     enligt detta Avtal
     som framgår av avtalsbilaga 1, prislista
     enligt prislista, bilaga 1,
     Avtalsbilaga 1- Prislista

 */

public class ReferenceExtractorSE extends ReferenceExtractor implements FeatureExtractorInterface {


    public ReferenceExtractorSE(){

        super();

        regExpList = new ArrayList<TextPattern>(){{

            //new TextPattern("[ enligt| i| framgår av| motsvaras av | enlighet med | per] [ punkt| kapitel| avsnitt| stycke| appendix| paragraf]* (\\d+[\\.\\d+]*)");          // ex: ( enligt punkt 27.1)
            add(new TextPattern("(?:enligt |i |framgår av | motsvaras av |per |enlighet med )(?:punkt |kapitel |avsnitt |stycke |appendix |paragraf )*(\\d+[\\.\\d+]*)"));      // ex: ( enligt punkt 27.1)
            add(new TextPattern("(?:se )(?:också |även )*(?:punkt |kapitel |avsnitt |stycke |appendix |paragraf )*(\\d+[\\.\\d+]*)"));                                          // ex: ( enligt punkt 27.1)

        }};


            patterns = new ArrayList<TextPattern>(){{

                add(new TextPattern(new String[] {"kallas", "(för|nedan|hädanefter|härefter)"}).veryClose().extractNextNoun());
                add(new TextPattern(new String[] {"(nedan|hädanefter|härefter)", "kallad"}).veryClose().extractNextNoun());

        }};



        name = "Referenser";
        searchKeywords = new String[] {};


    }



    @Override
    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree){


        // Language parameter to the common analysis
        String accordingToRegexp = "(?:enligt|framgår av| motsvaras av|per|enlighet med) (?:punkt |kapitel |avsnitt |stycke |appendix |paragraf )*";

        System.out.println(" --- Extracting Reference for :" + fragment.getText());

        super.classify(fragment, outcome, accordingToRegexp);

    }


}
