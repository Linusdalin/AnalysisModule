package classifiers.classifierTests;

import classifiers.englishClassifiers.BackgroundClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class BackgroundClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {


            new ClassificationTester("1.2 Background")
                     .withParser(englishParser)
                     .withHeadline("1.2 Background")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new BackgroundClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Background, 1)
                             .withTag("")
                     )
                 .test();

            new ClassificationTester("1.2 Background")
                     .withParser(englishParser)
                     .withHeadline(" Som other Background heading")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierEN())
                     .withClassifier(new BackgroundClassifierEN())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Background, 0)

                     )
                 .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


}
