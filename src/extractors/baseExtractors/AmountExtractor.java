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

@Deprecated
public abstract class AmountExtractor extends FeatureExtractor implements FeatureExtractorInterface {


    public AmountExtractor(LanguageInterface language){

        super();

        final NLParser recursiveParser = new NLParser(language, "TextAnalysis/models");

        patterns = new ArrayList<TextPattern>(){{

            add(new TextPattern(new String[]{"(sek|kronor|kr)", "#numex"}).veryClose().anyOrder()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(SECOND_WORD).withTag("SEK"));

            add(new TextPattern(new String[]{"(sek|kronor|kr)", "\\(", "#numex", "\\)"}).close()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(THIRD_WORD).withTag("SEK"));



            add(new TextPattern(new String[]{"(\\$|usd|dollar)", "#numex"}).veryClose().anyOrder()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(SECOND_WORD).withTag("USD"));

            add(new TextPattern(new String[]{"(\\$|usd|dollar)", "\\(", "#numex", "\\)"}).close()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(THIRD_WORD).withTag("USD"));


            add(new TextPattern(new String[]{"(€|eur|euro)", "#numex"}).veryClose().anyOrder()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(SECOND_WORD).withTag("EUR"));

            add(new TextPattern(new String[]{"(€|eur|euro)", "\\(", "#numex", "\\)"}).close()
                                                                        .withRecursion("#numex", new NumberExtractorSE(), recursiveParser)
                                                                        .extract(THIRD_WORD).withTag("EUR"));


            //add(new TextPattern(new String[] {"(USD|dollar)", "50"}).veryClose().anyOrder().extract( 2 ).withTag("USD"));                 // Testing
            //add(new TextPattern(new String[] {"(€|euro)", "50"}).veryClose().anyOrder().extract( 2 ).withTag("SEK"));                 // Testing

        }};

        type = FeatureTypeTree.Amount;


    }


}
