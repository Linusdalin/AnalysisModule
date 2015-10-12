package classifiers.classifierTests;

import classifiers.englishClassifiers.ArbitrationClassifierEN;
import classifiers.swedishClassifiers.ArbitrationClassifierSE;
import classifiers.swedishClassifiers.DefinitionUsageClassifierSV;
import classifiers.swedishClassifiers.NumberClassifierSV;
import classifiers.swedishClassifiers.WarrantyClassifierSV;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class WarrantyClassifierTest extends AnalysisTest {



    @BeforeClass
    public static void preAmble(){

        init();
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
                    .withClassifier(new ArbitrationClassifierSE())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();

            new ClassificationTester("No test")
                    .withParser(englishParser)
                    .withClassifier(new ArbitrationClassifierEN())
                    .expectingClassification(new ClassificationAssertion(FeatureTypeTree.ARBITRATION, 0)

                    )

                    .test();



        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }


    }


    /***********************************************************
        *
        *      Testing Classification by examples for tag #WARRANTY
        *      Document:   "Bilaga 3A - Ramavtal Interaktiva utbildningar.docx"
        *      Language:   "SV"
        *
        */


       @Test
       public void testLVExamples(){
           try {
              // Found 3 re-classifications

                  new ClassificationTester("14.4 Säljaren garanterar att av Leverantören hostad interaktiv utb"+
                                      "ildning uppfyller kapacitetskraven i Bilaga 2. Vid brist i kapacitetsk"+
                                      "raven, har Las Vegas rätt till ett vite vid var tillfälle om tio (10) "+
                                      "procent av månadsavgiften för hostingen av den aktuella utbildningen. "+
                                      "")
                           .withParser(swedishParser)
                           .withHeadline(" add headline...")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new WarrantyClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.WARRANTY, 1)
                                   .withPattern("Säljaren garanterar")

                           )
                       .test();




              } catch (Exception e) {
                   e.printStackTrace(System.out);
                   assertTrue(false);
              }
           }

}
