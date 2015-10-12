package classifiers.classifierTests;

import classifiers.swedishClassifiers.*;
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


public class ExemptionClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {



        } catch (Exception e) {
               e.printStackTrace();
               assertTrue(false);
        }

    }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #OBLIGATION
       *      Document:   "Bilaga 1 - Kravspecifikation webbutbildning,2014-04-28.docx"
       *      Language:   "SV"
       *
       */


      @Test
      public void testLVExamples(){
          try {


              new ClassificationTester("Säljaren kan dock inte garantera några volymer.")
                       .withParser(swedishParser)
                       .withHeadline(" add headline...")
                       .withProject(mockProject, mockDocument)
                       .withClassifier(new NumberClassifierSV())
                       .withClassifier(new DefinitionUsageClassifierSV())
                      .withClassifier(new PartsClassifierSV())
                       .withClassifier(new ExemptionClassifierSV())
                       .expectingClassification(new ClassificationAssertion(FeatureTypeTree.EXEMPTION, 1)
                               .withPattern("Säljaren kan dock inte garantera")

                       )
                   .test();



             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }

}
