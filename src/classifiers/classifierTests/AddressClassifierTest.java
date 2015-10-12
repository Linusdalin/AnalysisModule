package classifiers.classifierTests;

import classifiers.swedishClassifiers.AddressClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class AddressClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }


    /***********************************************************
     *
     *
     *
     */



    @Test
    public void SwedishAddressTest(){

        final String[] addressExamples = {

                "Linusvägen 32, 123 45 Stockholm",
                "Linusvägen 32, SE-123 45 Stockholm",
                "Myndigheten, SE-123 45 Stockholm",
        };

        try{

        for (String addressExample : addressExamples) {

            new ClassificationTester("Before " + addressExample + " after.")
                    .withParser(swedishParser)
                    .withClassifier(new AddressClassifierSV())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ADDRESS, 1)
                            .withPattern(addressExample)

                    )

                    .test();

        }



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
