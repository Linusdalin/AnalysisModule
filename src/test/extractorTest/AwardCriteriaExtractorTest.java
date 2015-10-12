package test.extractorTest;

import classifiers.classifierTests.ExtractorTesterOld;
import extractors.extractorsEN.AwardCriteriaExtractorEN;
import extractors.extractorsSE.AwardCriteriaExtractorSE;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;
import test.oldTests.FeatureTestCommon;

/**
 *
 */


public class AwardCriteriaExtractorTest extends FeatureTestCommon {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    @Test
    public void RegExpWordTest(){



        new ExtractorTesterOld("Det skall funka med stor bokstav för Tilldelningsgrund")
                .withParser(swedishParser)
                .withExtractor(new AwardCriteriaExtractorSE())
                .expectingType(FeatureTypeTree.ACCEPTANCE_CRITERIA)
                .expectingPattern("Tilldelningsgrund")
                .test(0, 1);

        new ExtractorTesterOld("Det skall funka med olika former för Tilldelningsgrunden")
                .withParser(swedishParser)
                .withExtractor(new AwardCriteriaExtractorSE())
                .expectingType(FeatureTypeTree.ACCEPTANCE_CRITERIA)
                .expectingPattern("Tilldelningsgrunden")
                .test(0, 1);





    }


    @Test
    public void BasicTest(){


            //TODO: We really would like to get a better extraction here. We want "ekonomiskt mest fördelaktigt"


            new ExtractorTesterOld("Chicago kommer i denna upphandling tillämpa tilldelningsgrunden ekonomiskt mest fördelaktigt.")
                    .withParser(swedishParser)
                    .withExtractor(new AwardCriteriaExtractorSE())
                    .expectingType(FeatureTypeTree.ACCEPTANCE_CRITERIA)
                    .expectingPattern("tilldelningsgrunden")
                    .test(0, 1);



        new ExtractorTesterOld("The effect of such amendments would be to allow vague, immeasurable elements, for the benefit of civil society in the broadest sense, to be used as award criteria.")
                .withParser(englishParser)
                .withExtractor(new AwardCriteriaExtractorEN())
                .expectingType(FeatureTypeTree.ACCEPTANCE_CRITERIA)
                .expectingPattern("award criteria")
                .test(0, 1);

        new ExtractorTesterOld("Mer detaljer om detta och utvärderingsmodellen framgår av avsnitt 4.")
                .withParser(swedishParser)
                .withExtractor(new AwardCriteriaExtractorSE())
                .expectingType(FeatureTypeTree.ACCEPTANCE_CRITERIA)
                .expectingPattern("utvärderingsmodellen")
                .test(0, 1);


    }
    @Test
    public void failTest(){

        new ExtractorTesterOld("Är tilldelning i grunden jämlik")
                .withParser(swedishParser)
                .withExtractor(new AwardCriteriaExtractorSE())
                .test(0, 0);

    }

}
