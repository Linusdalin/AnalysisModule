package extractors.extractorsSE;

import analysis.FeatureExtractorInterface;
import extractors.baseExtractors.DeadlineExtractor;
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

public class DeadlineExtractorSE extends DeadlineExtractor implements FeatureExtractorInterface {


    public DeadlineExtractorSE(LanguageInterface language){

        super( language );

        final NLParser recursiveParser = new NLParser(language, "TextAnalysis/models");

        patterns = new ArrayList<TextPattern>(){{

            add(new TextPattern(new String[]{"senast", "#date"}).veryClose().anyOrder()
                                                                        .withRecursion("#date", new DateExtractorSE(), recursiveParser)
                                                                        .extract(SECOND_WORD));

            add(new TextPattern(new String[]{"(sista|senast)", "\\b(anbud|svars)*(dag|tid)", "(för|att)", "#date"}).mostOf()
                                                                        .withRecursion("#date", new DateExtractorSE(), recursiveParser)
                                                                        .extract(FORTH_WORD));


        }};



        name = "Deadline";
        searchKeywords = new String[] {};

        type = FeatureTypeTree.Deadline;


    }


}
