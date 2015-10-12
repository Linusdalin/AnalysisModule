package test.extractorTest;

import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsEN.DeadlineExtractorEN;
import extractors.extractorsSE.DeadlineExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import test.oldTests.FeatureTestCommon;

import static org.junit.Assert.assertTrue;

/**
 *
 */


public class DeadlineExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void basicTest(){


        try {


            new ExtractorTesterOld("senast 2014-07-01")
                    .withParser(swedishParser)
                    .withExtractor(new DeadlineExtractorSE(swedishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectingPattern("2014-07-01")
                    .test(0, 1);

            new ExtractorTesterOld("sista dag för att lämna in bla bla är 2014-07-01")
                    .withParser(swedishParser)
                    .withExtractor(new DeadlineExtractorSE(swedishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectingPattern("2014-07-01")
                    .test(0, 1);

            new ExtractorTesterOld("sista svarsdag  är 2014-07-01")
                    .withParser(swedishParser)
                    .withExtractor(new DeadlineExtractorSE(swedishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectingPattern("2014-07-01")
                    .test(0, 1);




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }

    @Test
    public void basicTestEnglish(){


        try {


            new ExtractorTesterOld("at latest 2014-07-01")
                    .withParser(englishParser)
                    .withExtractor(new DeadlineExtractorEN(englishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectingPattern("2014-07-01")
                    .test(0, 1);

            new ExtractorTesterOld("no later than 2014-07-01")
                    .withParser(englishParser)
                    .withExtractor(new DeadlineExtractorEN(englishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectingPattern("2014-07-01")
                    .test(0, 1);




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }



    @Test
    public void handleTwoDates(){


        try {


            new ExtractorTesterOld("Referenttagning mellan kl.09.00-12.00 och 13.00-15.00 2014-06-16—2014-06-18")
                    .withParser(englishParser)
                    .withExtractor(new DeadlineExtractorEN(englishParser.getLanguage()))
                    .expectingType(FeatureTypeTree.Deadline)
                    .expectToFail();


        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }




}
