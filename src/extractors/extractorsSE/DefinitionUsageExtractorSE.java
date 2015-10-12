package extractors.extractorsSE;

import analysis.AnalysisOutcome;
import analysis.FeatureExtractorInterface;
import extractors.DefinitionUsageExtractor;
import featureTypes.FeatureTypeTree;
import parse.AnalysisFragment;

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

public class DefinitionUsageExtractorSE extends DefinitionUsageExtractor implements FeatureExtractorInterface {


    public DefinitionUsageExtractorSE(){

        super();

        name = "Användande av Definition";
        searchKeywords = new String[] {};


    }

    @Override
    public void classify(AnalysisFragment fragment, AnalysisOutcome outcome, FeatureTypeTree tree){


        // Language parameter to the common analysis
        String tag = "Tvetydig definitionsreferens";


        super.classify(fragment, outcome, tag);

    }


}
