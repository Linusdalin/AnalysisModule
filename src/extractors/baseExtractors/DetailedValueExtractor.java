package extractors.baseExtractors;

import analysis.FeatureExtractor;
import analysis.FeatureExtractorInterface;
import extractors.extractorsSE.NumberExtractorSE;
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

public abstract class DetailedValueExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public DetailedValueExtractor(LanguageInterface language){

        super();

        final NLParser recursiveParser = new NLParser(language, "TextAnalysis/models");

        patterns = new ArrayList<TextPattern>(){{

            add(new TextPattern(new String[]{"\\(", "#numex", "\\)"}).adjacent()
                    .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                    .extract(SECOND_WORD));


        }};




        type = FeatureTypeTree.DetailedValue;


    }


}
