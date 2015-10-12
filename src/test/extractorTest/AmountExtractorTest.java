package test.extractorTest;

import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsSE.AmountExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import test.oldTests.FeatureTestCommon;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 */


public class AmountExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void SEKAmount(){



        new ExtractorTesterOld("Det kostar 50 SEK")
                .withParser(swedishParser)
                .withExtractor(new AmountExtractorSE( new Swedish()))
                .expectingType(FeatureTypeTree.Amount)
                .expectingPattern("50")
                .expectingTag("SEK")
                .test(0, 1);


        new ExtractorTesterOld("Det kostar 50 dollar")
                .withParser(swedishParser)
                .withExtractor(new AmountExtractorSE(new Swedish()))
                .expectingType(FeatureTypeTree.Amount)
                .expectingPattern("50")
                .expectingTag("USD")
                .test(0, 1);

        new ExtractorTesterOld("Hyran utgår med 23´000 kr per månad och ska betalas kvartalsvis i förskott")
                .withParser(swedishParser)
                .withExtractor(new AmountExtractorSE(new Swedish()))
                .expectingType(FeatureTypeTree.Amount)
                .expectingPattern("23´000", 16)
                .expectingTag("SEK")
                .test(0, 1);

        new ExtractorTesterOld("Det kostar 50kr")
                .withParser(englishParser)
                .withExtractor(new AmountExtractorSE(new Swedish()))
                .expectingType(FeatureTypeTree.Amount)
                .expectingPattern("50")
                .expectingTag("SEK")
                .test(0, 1);


    }

    @Test
    public void DetailedValueAmount(){



        new ExtractorTesterOld("Det kostar FEMTIO ( 50 ) SEK")
                .withParser(swedishParser)
                .withExtractor(new AmountExtractorSE( new Swedish()))
                .expectingType(FeatureTypeTree.Amount)
                .expectingPattern("50")
                .expectingTag("SEK")
                .test(0, 1);

    }


    @Test
    public void failTest(){

        new ExtractorTesterOld("Det kostar 200 pengar")
                .withParser(englishParser)
                .withExtractor(new AmountExtractorSE(new Swedish()))
                .test(0, 0);

    }

}
