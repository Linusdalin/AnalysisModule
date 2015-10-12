package classifiers.classifierTests;

import classifiers.englishClassifiers.StructureClassifierEN;
import classifiers.englishClassifiers.TermAndTerminationClassifierEN;
import classifiers.swedishClassifiers.StructureClassifierSE;
import classifiers.swedishClassifiers.TermAndTerminationClassifierSE;
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


public class StructureClassifierTest extends AnalysisTest {

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
                    .withClassifier(new StructureClassifierSE())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Structure, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new StructureClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Structure, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
