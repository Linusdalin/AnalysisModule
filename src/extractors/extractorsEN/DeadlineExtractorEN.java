package extractors.extractorsEN;

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

public class DeadlineExtractorEN extends DeadlineExtractor implements FeatureExtractorInterface {


    public DeadlineExtractorEN(LanguageInterface language){

        super( language );

        final NLParser recursiveParser = new NLParser(language, "TextAnalysis/models");

        patterns = new ArrayList<TextPattern>(){{

            add(new TextPattern(new String[]{"latest", "#date"}).veryClose().anyOrder()
                                                                        .withRecursion("#date", new DateExtractorEN(), recursiveParser)
                                                                        .extract(SECOND_WORD));

            add(new TextPattern(new String[]{"(last|latest)", "\\b(day|time)", "(for|to)", "#date"}).mostOf()
                                                                        .withRecursion("#date", new DateExtractorEN(), recursiveParser)
                                                                        .extract(FORTH_WORD));

            add(new TextPattern(new String[]{"later", "than",  "#date"}).mostOf()
                                                                        .withRecursion("#date", new DateExtractorEN(), recursiveParser)
                                                                        .extract(THIRD_WORD));


        }};


        name = "Deadline";
        searchKeywords = new String[] {};

        type = FeatureTypeTree.Deadline;


    }


}
