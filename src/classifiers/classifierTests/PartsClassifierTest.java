package classifiers.classifierTests;

import classifiers.swedishClassifiers.PartyUsageClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class PartsClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
     *
     *          Usage is similar to definitions
     *
     */

    @Test
    public void basicTest(){


        try {


            new ClassificationTester("Endera Part kan ")
                    .withParser(swedishParser)
                    .withClassifier(new PartyUsageClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PARTY_USAGE, 1)
                            .withPattern("Endera Part")

                    )

                    .test();


            new ClassificationTester("Part som vill ")
                    .withParser(swedishParser)
                    .withClassifier(new PartyUsageClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.PARTY_USAGE, 1)
                            .withPattern("Part")

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
