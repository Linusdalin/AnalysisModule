package classifiers.classifierTests;

import classifiers.englishClassifiers.LegalEntityClassifierEN;
import classifiers.swedishClassifiers.LegalEntityClassifierSV;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class LegalClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
     *
     *          Not implemented classifier, so the tests should not work
     *
     */

    @Test
    public void basicTest(){


        try {


            new ClassificationTester("No test")
                    .withParser(swedishParser)
                    .withClassifier(new LegalEntityClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Legal, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new LegalEntityClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Legal, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
