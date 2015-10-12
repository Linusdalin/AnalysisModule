package classifiers.classifierTests;

import classifiers.englishClassifiers.TermsClassifierEN;
import classifiers.swedishClassifiers.TermsClassifierSV;
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


public class TermsClassifierTest extends AnalysisTest {

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
                    .withClassifier(new TermsClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMS, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new TermsClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.TERMS, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
